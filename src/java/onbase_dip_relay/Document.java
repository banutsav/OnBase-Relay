package onbase_dip_relay;

import org.apache.struts.upload.FormFile;

/**
 *
 * @author Glenn Owens
 */
public class Document implements java.io.Serializable {

    private FormFile formFile;
    public String fileName = "";
    public String fileSize;

    public FormFile getFormFile() {
        return formFile;
    }

    public void setFormFile(FormFile formFile) {
        this.formFile = formFile;
        fileName = formFile.getFileName();
        fileSize = String.valueOf(formFile.getFileSize());
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileSize() {
        return fileSize;
    }
}