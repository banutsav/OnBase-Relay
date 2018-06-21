/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onbase_dip_relay.osp_award;

/**
 *
 * @author utsavb
 */
public class Content7IndexFile {
    
    private String content7IndexFileText = "";
    public void addHeaders()
    {
        content7IndexFileText += "TempNumber|Sponsor|PI First Name|PI Last Name|Unit|UGA Prop Number|Sponsor Code|DocTypeName|FullPath";
        content7IndexFileText += "\r\n";
    }
    public void createIndexFile(AdditionalDocumentAttributes docAttributes, String path)
    {
        content7IndexFileText += docAttributes.getPTLNumber();
        content7IndexFileText += "|";
        
        content7IndexFileText += docAttributes.getSponsor();
        content7IndexFileText += "|";
        
        content7IndexFileText += docAttributes.getPIFirstName();
        content7IndexFileText += "|";
        
        content7IndexFileText += docAttributes.getPILastName();
        content7IndexFileText += "|";
        
        content7IndexFileText += docAttributes.getsubmittingDepartment();
        content7IndexFileText += "|";
        
        content7IndexFileText += docAttributes.getUGAPropNumber();
        content7IndexFileText += "|";
        
        content7IndexFileText += docAttributes.getSponsorCode();
        content7IndexFileText += "|";
        
        content7IndexFileText += docAttributes.getDocTypeName();
        content7IndexFileText += "|";
    
        content7IndexFileText += path;
        content7IndexFileText += "|";
    }
    /*
        Method to retrieve the entire file text for an Object of this Class
    */
    public String getContent7IndexFileText()
    {
        return content7IndexFileText;
    }
}
