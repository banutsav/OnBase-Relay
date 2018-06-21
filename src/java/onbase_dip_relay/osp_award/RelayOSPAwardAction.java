package onbase_dip_relay.osp_award;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import onbase_dip_relay.FTPUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Glenn Owens Method handles the data sent by a POST request. Data sent
 * by click application "uploadAwardDocumentsToOnbase" inside "CustomUtils"
 * (Consult the entity manager at ovpr-grants-dev.ovpr.uga.edu for the click
 * application) Click application sends Award documents. These are collected and
 * stored by this method. An index file is also created for OnBase to reference
 * the documents. Consult the WEB-INF/ftp-properties file for the location where
 * the documents are stored.
 */
public class RelayOSPAwardAction extends Action {

    private static final String docTypeName = "OSPAward";

    /*
     required for establishing FTP connection - doFTPTransfer()
     */
    private int ftpPort;
    private String ftpHost;
    private String ftpUsername;
    private String ftpPassword;
    private String ftpRootFolder;

    // F&A Onbase Server credentials
    private int FAftpPort;
    private String FAftpHost;
    private String FAftpUsername;
    private String FAftpPassword;
    private String FAftpFolder;
    private String SMBMountLocation;
    private String Content7MountLocation;

    /*
     required to store the contents of the index file,
     location of documents, document list and index file name - processFormFile()
     */
    private String importIndexFileText;
    private String importPTLFileText;
    private String identifier;
    private String iifFileName;
    private String ptlFileName;
    private String docFolder;
    private RelayOSPAwardForm c_form;
    private Properties emailProps;
    private String fullPath;

    /*
     array to store individual documents of the document set
     used in transferring documents - doFTPTransfer()
     */
    private ArrayList<byte[]> fileDataList;

    /*
     stores attributes for a set of documents, to be 
     written as entries into the index file - initialize()
     */
    private AdditionalDocumentAttributes docAttributes;

    /*
     object to store the contents of the index file
     */
    private importIndexFile indexFile;
    
    private Content7IndexFile content7IndexFile;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            System.out.println("---------------------------------");
            System.out.println("Starting onbase document transfer");
            initialize(request, form);
            processFormFile();
            
            /* THE FOLLOWING HAVE BEEN DISCONTINUED
            //doFTPTransfer();
            //System.out.println("Starting FA onbase document transfer");
            //doFTPTransferFA();
            */
            
            //UNCOMMENT!!!
            System.out.println("Writing to SMB Mount");
            writeSMBMount();
            System.out.println("document transfer finished");
            
            // STILL IN DEV
            //System.out.println("Content7 Index File Text = ");
            //System.out.println(content7IndexFile.getContent7IndexFileText());
            
            //Writing to CONTENT7
            //writeContent7SMBMount();
            
            return null;
        } catch (Exception ex) {
            servlet.log(" Error message", ex);
            throw ex;//return a Tomcat Error page with exception stacktrace
            //return mapping.findForward("gfwd_error");
        }
    }

    /*
     initializes variables rewuired for FTP connection,
     document folder location, index file name, 
     object to store contents of index file
     */
    public void initialize(HttpServletRequest request, ActionForm form) throws Exception {
        try {
            c_form = (RelayOSPAwardForm) form;

            /*
             get location for storing the relayed documents.
             consult file WEB-INF/ftp-properties
             */
            emailProps = new Properties();
            emailProps.load(request.getSession().getServletContext().getResourceAsStream("/WEB-INF/ftp.properties"));

            ftpHost = emailProps.getProperty("ftpHost");
            ftpPort = Integer.valueOf(emailProps.getProperty("ftpPort"));
            ftpUsername = emailProps.getProperty("ftpUsername");
            ftpPassword = emailProps.getProperty("ftpPassword");
            ftpRootFolder = emailProps.getProperty("ftpRootFolder");

            // F&A connection credentials
            FAftpHost = emailProps.getProperty("FAftpHost");
            FAftpPort = Integer.valueOf(emailProps.getProperty("FAftpPort"));
            FAftpUsername = emailProps.getProperty("FAftpUsername");
            FAftpPassword = emailProps.getProperty("FAftpPassword");
            FAftpFolder = emailProps.getProperty("FAftpFolder");
            SMBMountLocation = emailProps.getProperty("SMBMountLocation");
            Content7MountLocation = emailProps.getProperty("Content7MountLocation");
            fileDataList = new ArrayList();

            /*
             Create a docAttributes object which stores attributes of a document set
             document set = set of documents to be uploaded to OnBase
             Attributes include = name of sponsor, PI Name, Department, UGA Proposal Number
             */
            docAttributes = new AdditionalDocumentAttributes(request);

            /*
             Object to construct the file contents of the index file
             Index file enables the OnBase web service to download documents from ovpr-onbase.ovpr.uga.edu
             */
            indexFile = new importIndexFile();

            //content7 index file
            content7IndexFile = new Content7IndexFile();
            
            /*
             index file is named = "IIF_"
             documents are stored inside the folder = "DF_"
             */
            identifier = c_form.getProposalNum();
            ptlFileName = "IIF_PTL.dip";
            iifFileName = "IIF_" + identifier + ".dip";
            docFolder = "DF_" + identifier;

        } catch (IOException ex) {
            throw ex;
        } catch (NumberFormatException ex) {
            throw ex;
        }

    }

    public void processFormFile() throws Exception {

        // add headers for content7 index file
        content7IndexFile.addHeaders();
        
        //ptl entry for normal index file
        indexFile.addPTLEntry(docAttributes);

        /*
         retrieve each document from the form file set and
         store it into array after encoding.
         Add index file entry for each document in the list
         */
        String delims = ",";
        String tokens[] = docAttributes.getfiletype_concat().split(delims);

        for (int i = 0; i < c_form.getDocuments().size(); i++) {
            InputStream in = null;

            try {
                //Process Form File
                System.out.println("doc = " + c_form.getDocument(i).getFileName());
                System.out.println("form file = " + c_form.getDocument(i).getFormFile());
                //System.out.println("input stream = " + c_form.getDocument(i).getFormFile().getInputStream());

                in = c_form.getDocument(i).getFormFile().getInputStream();

                final ByteArrayOutputStream outEncoded = new ByteArrayOutputStream();
                int next = in.read();

                while (next > -1) {
                    outEncoded.write(next);
                    next = in.read();
                }

                outEncoded.flush();
                final byte[] arrEncoded = outEncoded.toByteArray();
                final byte[] arrDecoded = Base64.decodeBase64(arrEncoded);
                fileDataList.add(arrDecoded);

                /*
                 Construct the file path = location of the document inside the OnBase host
                 Typically the host is ovpr-onbase.ovpr.uga.edu
                 Add this filepath to the index file
                 This enables the OnBase web service to locate and pick up the document
                 */
                fullPath = ftpRootFolder + "\\" + docTypeName + "\\" + docFolder + "\\" + c_form.getDocument(i).getFileName();

                //create the content7 index file
                content7IndexFile.createIndexFile(docAttributes, fullPath);

                //System.out.println("filetype = " + docAttributes.getFileType());
                indexFile.addFirstIndexFileEntry(docAttributes);
                indexFile.addIndexFileEntry(fullPath);
                indexFile.addOtherIndexValues(docAttributes, tokens[i]);

                //indexFile.endIndexFileEntry();
            } catch (IOException ex) {
                throw ex;
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        }

        /*
         Index File entries are completed
         The EOF marker is placed
         variable importIndexFileText holds the final text contentx of the text file
         */
        indexFile.endIndexFileEntry();

        importIndexFileText = indexFile.getIndexFileText();
        importPTLFileText = indexFile.getPTLFileText();

    }

    /**
     * Transfer documents to the onBase location via FTP.
     *
     * @throws Exception
     */
    public void doFTPTransfer() throws Exception {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_NT);
            config.setLenientFutureDates(true);//Fix for https://issues.apache.org/jira/browse/NET-159
            ftpClient.configure(config);

            ftpClient.connect(ftpHost, ftpPort);

            ftpClient.login(ftpUsername, ftpPassword);

            final int reply = ftpClient.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new Exception("FTPS Connection Failed. Got Reply Code: " + reply);
            }

            System.out.println("Can connect to " + ftpHost + ", reply = " + reply);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            /*
             check if directory to store documents exist on onBase host.
             If not then create it, if it does do not create a new one.
             Prevents existing contents of the DF_ directory from being erased.
             The onBase web service which picks up the documents is responsible
             for removing directory contents once it is done uploading.
             */
            if (FTPUtils.checkDirectoryExists(ftpClient, docTypeName + "/" + docFolder) == false) {
                ftpClient.makeDirectory(docTypeName + "/" + docFolder);
            }

            for (int i = 0; i < fileDataList.size(); i++) {
                System.out.println("Storing File: " + docTypeName + "/" + docFolder + "/" + c_form.getDocument(i).getFileName());
                ftpClient.storeFile(docTypeName + "/" + docFolder + "/" + c_form.getDocument(i).getFileName(), new ByteArrayInputStream(fileDataList.get(i)));
            }

            /*
             add entry to the index file for each document in the the list 
             */
            System.out.println("DEBUG: index file location = " + docTypeName + "/" + iifFileName);

            ftpClient.appendFile(docTypeName + "/" + iifFileName, new ByteArrayInputStream(importIndexFileText.getBytes()));
            ftpClient.appendFile(docTypeName + "/" + ptlFileName, new ByteArrayInputStream(importPTLFileText.getBytes()));
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
    }

    public void writeSMBMount() {
        System.out.println("Writing IIF file = " + SMBMountLocation + "/" + iifFileName);
        try {
            File iifFile = new File(SMBMountLocation + "/" + iifFileName);
            File ptlFile = new File(SMBMountLocation + "/" + ptlFileName);
            if (!iifFile.exists()) {
                iifFile.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(iifFile, true));
            writer.write(importIndexFileText);
            writer.close();
            
            // don't generate PTL file if it is empty
            if (!importPTLFileText.isEmpty()) {
                System.out.println("Writing PTL file = " + SMBMountLocation + "/" + ptlFileName);
                if (!ptlFile.exists()) {
                    ptlFile.createNewFile();
                }
                BufferedWriter ptlwriter = new BufferedWriter(new FileWriter(ptlFile, true));
                ptlwriter.write(importPTLFileText);
                ptlwriter.close();
            }
            
            System.out.println("Storing Documents on Mount...");
            for (int i = 0; i < fileDataList.size(); i++) {
                System.out.println("Storing File: " + SMBMountLocation + "/" + FAftpFolder + c_form.getDocument(i).getFileName());
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileDataList.get(i));
                IOUtils.copy(byteArrayInputStream, new FileOutputStream(SMBMountLocation + "/" + FAftpFolder + "/" + c_form.getDocument(i).getFileName()));
            }
                    
            
        } catch (Exception ex) {
            System.out.println("Error writing to file system: " + ex.toString());
            ex.printStackTrace();
        }

    }

    //Writing SMB mount to Content7
    public void writeContent7SMBMount() {
        System.out.println("Writing Index file to CONTENT7 = " + Content7MountLocation + "/" + iifFileName);
        try {
            File iifFile = new File(Content7MountLocation + "/" + iifFileName);
            
            if (!iifFile.exists()) {
                iifFile.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(iifFile, true));
            writer.write(content7IndexFile.getContent7IndexFileText());
            writer.close();
            
            System.out.println("Storing Documents on Mount...");
            for (int i = 0; i < fileDataList.size(); i++) {
                System.out.println("Storing File: " + Content7MountLocation + "/" +  c_form.getDocument(i).getFileName());
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileDataList.get(i));
                IOUtils.copy(byteArrayInputStream, new FileOutputStream(Content7MountLocation + "/" + c_form.getDocument(i).getFileName()));
            }
                    
            
        } catch (Exception ex) {
            System.out.println("Error writing to file system: " + ex.toString());
            ex.printStackTrace();
        }

    }

    
    public void doFTPTransferFA() {

        JSch jsch = new JSch();
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp;
        try {
            session = jsch.getSession(FAftpUsername, FAftpHost, FAftpPort);
            session.setConfig("StrictHostKeyChecking", "no"); // should be avoided
            session.setConfig("PreferredAuthentications", "password");
            session.setPassword(FAftpPassword);
            session.setTimeout(30000);
            System.out.println("Trying to connect to FA server...");

            // try multiple times to connect
            for (int i = 0; i < 5; i++) {
                try {
                    session.connect();
                    System.out.println("Connected on try = " + (i + 1));
                    break;
                } catch (JSchException ex) {
                    System.out.println("Connection failed for try " + (i + 1));
                    if (i == 4) {
                        throw ex;
                    }
                    continue;
                }
            }

            System.out.println("Connected...");
            channel = session.openChannel("sftp");
            ChannelSftp sftp = (ChannelSftp) channel;
            sftp.connect();
            channelSftp = (ChannelSftp) channel;
            System.out.println("Working directory = " + channelSftp.pwd());
            System.out.println("Sending IIF files = " + iifFileName + ", " + ptlFileName);
            System.out.println("Index file = \n" + importIndexFileText + "\n");
            channelSftp.put(new ByteArrayInputStream(importIndexFileText.getBytes()), iifFileName, ChannelSftp.APPEND);

            // don't generate PTL file if it is empty
            if (!importPTLFileText.isEmpty()) {
                System.out.println("PTL file to be created...");
                System.out.println("PTL file contents=" + importPTLFileText);
                channelSftp.put(new ByteArrayInputStream(importPTLFileText.getBytes()), ptlFileName, ChannelSftp.APPEND);
            }

            channelSftp.cd(FAftpFolder);
            System.out.println("Sending Documents...");
            for (int i = 0; i < fileDataList.size(); i++) {
                System.out.println("Storing File: " + c_form.getDocument(i).getFileName());
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileDataList.get(i));
                channelSftp.put(byteArrayInputStream, c_form.getDocument(i).getFileName());
            }

            System.out.println("FA server upload complete...");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }

    }
}
