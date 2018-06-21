package onbase_dip_relay;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPUtils {

    /**
     * Removes a non-empty directory by delete all its sub files and sub
     * directories recursively. And finally remove the directory.
     * (NOT USED CURRENTLY, REMOVAL OF DIRECTORY CONTENTS IS DONE BY onBase service)
     */
    public static void removeDirectory(FTPClient ftpClient, String parentDir, String currentDir) throws Exception {
        String dirToList = parentDir;
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }

        final FTPFile[] subFiles = ftpClient.listFiles(dirToList);

        if (subFiles != null && subFiles.length > 0) {
            for (FTPFile aFile : subFiles) {
                final String currentFileName = aFile.getName();
                if (currentFileName.equals(".") || currentFileName.equals("..")) {
                    // skip parent directory and the directory itself
                    continue;
                }
                String filePath = parentDir + "/" + currentDir + "/" + currentFileName;
                if (currentDir.equals("")) {
                    filePath = parentDir + "/" + currentFileName;
                }

                if (aFile.isDirectory()) {
                    //remove the sub directory
                    removeDirectory(ftpClient, dirToList, currentFileName);
                } else {
                    //delete the file
                    final boolean deleted = ftpClient.deleteFile(filePath);
                    if (!deleted) {
                        throw new Exception("CANNOT delete the file: " + filePath);
                    }
                }
            }

            //finally, remove the directory itself
            boolean removed = ftpClient.removeDirectory(dirToList);
            if (!removed) {
                throw new Exception("CANNOT remove the directory: " + dirToList);
            }
        }
    }
    
    /*
        check to see if the directory where documents are to be transferred exists or not.
        If it exists, a new directory is not created. 
        Hence existing data in the directory is preserved.
        The onBase service which picks up the files is reponsible for deleting directory contents.
    */
    public static boolean checkDirectoryExists(FTPClient ftpClient, String dirPath) throws IOException 
    {
        /*
            keep the current directory location.
            See if it is possible to change the directory path.
            If possible means directory exists.
            At the end of the test restore the path to it's previous value.
        */
        
        String currDir = ftpClient.printWorkingDirectory();
        ftpClient.changeWorkingDirectory(dirPath);
        int returnCode = ftpClient.getReplyCode();
        ftpClient.changeWorkingDirectory(currDir); 
        return returnCode != 550;
    }
}