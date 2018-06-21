 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package onbase_dip_relay.osp_award;

/**
 *
 * @author utsav banerjee
 * Stores the contents of the index file required by OnBase
 * Index File is written to every time a user wants to upload award documentation
 * OnBase web services periodically monitor this and upload the documents
 * Once they upload it, the contents of the index file is erased by them
 */
public class importIndexFile {
    
    /*
        Private Variable which stores the text contents of the index file
        Index File is used by OnBase web services to upload award documents to OnBase
    */
    
    private String importIndexFileText = "";
    private String importPTLFileText = "";
    
    public void addPTLEntry(AdditionalDocumentAttributes docAttributes)
    {
        /*
            check if ptl number exists
        */
        
        System.out.println("DEBUG: ptl check");
        System.out.println("DEBUG: " + docAttributes.getptlExists());
        
        if(docAttributes.getptlExists().equals("no")) //UNCOMMENT!!!
        {
            System.out.println("DEBUG: adding PTL entry");
            
            importPTLFileText += "\r\n";
            
            importPTLFileText += "BEGIN:";
            importPTLFileText += "\r\n";
            importPTLFileText += "DocTypeName: OSP - PTL (eform)"; // + docAttributes.getDocTypeName();
            importPTLFileText += "\r\n";
            importPTLFileText += "Temp Number: " + docAttributes.getPTLNumber();
            importPTLFileText += "\r\n";
            importPTLFileText += "FullPath: C:\\OnBaseDIP\\OSPAward\\PTL\\951098.html";
            importPTLFileText += "\r\n";
            
            importPTLFileText += "Sponsor: " + docAttributes.getSponsor();
            importPTLFileText += "\r\n";
            importPTLFileText += "PI First Name: " + docAttributes.getPIFirstName();
            importPTLFileText += "\r\n";
            importPTLFileText += "PI Last Name: " + docAttributes.getPILastName();
            importPTLFileText += "\r\n";
       
            importPTLFileText += "Unit: " + docAttributes.getsubmittingDepartment();
            importPTLFileText += "\r\n";
        
            importPTLFileText += "UGA Prop Number: " + docAttributes.getUGAPropNumber();
            importPTLFileText += "\r\n";
            importPTLFileText += "CG RS Email: " + docAttributes.getCGResponsibleStaffEmail();
            importPTLFileText += "\r\n";
            importPTLFileText += "Inv Email: " + docAttributes.getInvoicingContactEmail();
            importPTLFileText += "\r\n";
            importPTLFileText += "Selection: " + docAttributes.getSelection(); // hard coded
            importPTLFileText += "\r\n";
            importPTLFileText += "OSP RS Email: " + docAttributes.getOSPRSEmail();
            importPTLFileText += "\r\n";
            importPTLFileText += "Sponsor Code: " + docAttributes.getSponsorCode();
            importPTLFileText += "\r\n";
        
            importPTLFileText += "PIEmail: " + docAttributes.getPIEmail();
            importPTLFileText += "\r\n";
        
            importPTLFileText += "PrimaryContactEmail: " + docAttributes.getPrimaryContactEmail();
            importPTLFileText += "\r\n";
        
            importPTLFileText += "SecContactEmail: " + docAttributes.getSecondaryContactEmail();
            importPTLFileText += "\r\n";
        
            importPTLFileText += "CGPrimContactEmail: " + docAttributes.getCGPrimaryContactEmail();
            importPTLFileText += "\r\n";
        
            importPTLFileText += "CGSecContactEmail: " + docAttributes.getCGSecondaryContactEmail();
            importPTLFileText += "\r\n";
       
            importPTLFileText += "CG RS: " + docAttributes.getCGRS();
            importPTLFileText += "\r\n";
      
            /*
            add file type entry
            */
       
            //importPTLFileText += "File Type: " + docAttributes.getFileType();
            importPTLFileText += "File Type: 24";
            importPTLFileText += "\r\n";
            
            importPTLFileText += "expedite: " + docAttributes.getExpedite();
            importPTLFileText += "\r\n";
            
            importPTLFileText += "END OF FILE";            
            importPTLFileText += "\r\n";
        }
    }
    
    public void addFirstIndexFileEntry(AdditionalDocumentAttributes docAttributes)
    {
        importIndexFileText += "\r\n";
            
        importIndexFileText += "BEGIN:";
        importIndexFileText += "\r\n";
        importIndexFileText += "DocTypeName: " + docAttributes.getDocTypeName();
        importIndexFileText += "\r\n";
        importIndexFileText += "Temp Number: " + docAttributes.getPTLNumber();
        importIndexFileText += "\r\n";
        
    }
    
    /*
        contact information required by OnBase for the document upload
    */
    
    public void addOtherIndexValues(AdditionalDocumentAttributes docAttributes, String filetype)
    {
        importIndexFileText += "Sponsor: " + docAttributes.getSponsor();
        importIndexFileText += "\r\n";
        importIndexFileText += "PI First Name: " + docAttributes.getPIFirstName();
        importIndexFileText += "\r\n";
        importIndexFileText += "PI Last Name: " + docAttributes.getPILastName();
        importIndexFileText += "\r\n";
       
        importIndexFileText += "Unit: " + docAttributes.getsubmittingDepartment();
        importIndexFileText += "\r\n";
        
        importIndexFileText += "UGA Prop Number: " + docAttributes.getUGAPropNumber();
        importIndexFileText += "\r\n";
        importIndexFileText += "CG RS Email: " + docAttributes.getCGResponsibleStaffEmail();
        importIndexFileText += "\r\n";
        importIndexFileText += "Inv Email: " + docAttributes.getInvoicingContactEmail();
        importIndexFileText += "\r\n";
        importIndexFileText += "Selection: " + docAttributes.getSelection(); // hard coded
        importIndexFileText += "\r\n";
        importIndexFileText += "OSP RS Email: " + docAttributes.getOSPRSEmail();
        importIndexFileText += "\r\n";
        importIndexFileText += "Sponsor Code: " + docAttributes.getSponsorCode();
        importIndexFileText += "\r\n";
        
        importIndexFileText += "PIEmail: " + docAttributes.getPIEmail();
        importIndexFileText += "\r\n";
        
        importIndexFileText += "PrimaryContactEmail: " + docAttributes.getPrimaryContactEmail();
        importIndexFileText += "\r\n";
        
        importIndexFileText += "SecContactEmail: " + docAttributes.getSecondaryContactEmail();
        importIndexFileText += "\r\n";
        
        importIndexFileText += "CGPrimContactEmail: " + docAttributes.getCGPrimaryContactEmail();
        importIndexFileText += "\r\n";
        
        importIndexFileText += "CGSecContactEmail: " + docAttributes.getCGSecondaryContactEmail();
        importIndexFileText += "\r\n";
       
        importIndexFileText += "CG RS: " + docAttributes.getCGRS();
        importIndexFileText += "\r\n";
        
        /*
            add "file type" entry
        */
       
        importIndexFileText += "File Type: " + docAttributes.getFileType();
        //importIndexFileText += "File Type: " + filetype;
        importIndexFileText += "\r\n";
        
        importIndexFileText += "expedite: " + docAttributes.getExpedite();
        importIndexFileText += "\r\n";
        
    }
    
    /*
        Method to write Physical File Location for each award document
        Location is specified by the fullPath variable
    */
    public void addIndexFileEntry(String fullPath)
    {
        importIndexFileText += "FullPath: " + fullPath;
        importIndexFileText += "\r\n";
    }
    
    /*
        Method to place the EOF marker for the index file
    */
    public void endIndexFileEntry()
    {
        importIndexFileText += "END OF FILE";            
        importIndexFileText += "\r\n";
    }
    
    /*
        Method to retrieve the entire file text for an Object of this Class
    */
    public String getIndexFileText()
    {
        return importIndexFileText;
    }
    
    public String getPTLFileText()
    {
        return importPTLFileText;
    }
}
