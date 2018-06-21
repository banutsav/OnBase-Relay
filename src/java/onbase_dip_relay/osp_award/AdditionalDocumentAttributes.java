/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package onbase_dip_relay.osp_award;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author utsav banerjee
 * Class which is used to store extra attributes for a collection of documents.
 * They are stored as part of the HTTP request header.
 * These are common attributes for a set of documents.
 * Look for uploadDocsToOnbase() inside of CustomUtils in the Entity Manager for Click
 * for details on how the Click web service sets these attributes before sending the HTTP POST
 */
public class AdditionalDocumentAttributes {
    
    /*
        Private variables to describe the different attributes which a set of award documents has
        These values are required for OnBase web services to upload the documents to OnBase
    */
    
    private String Selection = "";
    private String DocTypeName = "";
    private String sponsor = "";
    private String ownerFirstName = "";
    private String ownerLastName = "";
    private String PIFirstName = "";
    private String PILastName = "";
    private String PIEmail = "";
    private String submittingDepartment = "";
    private String UGAPropNumber = "";
    private String CGResponsibleStaffEmail = "";
    private String InvoicingContactEmail = "";
    private String OSPRSEmail = "";
    private String SponsorCode = "";
    private String PTLNumber = "";
    private String PrimaryContactEmail = "";
    private String SecondaryContactEmail = "";
    private String CGPrimaryContactEmail = "";
    private String CGSecondaryContactEmail = "";
    private String ptlExists = "";
    private String CGRS = "";
    private String FileType = "";
    private String expedite = "";
    private String filetype_concat = "";
    
    /*
        Constructor to retrieve values from the HTTP Request header
        These values are the attribute values for a set of documents to be uploaded to OnBase
        Attribute values are used to construct an index file 
        Index File is used by OnBase web services to upload the documents to OnBase
    */
    
    public AdditionalDocumentAttributes(HttpServletRequest request)
    {
        sponsor = request.getHeader("sponsor");
        ownerFirstName = request.getHeader("ownerFirstName"); 
        ownerLastName = request.getHeader("ownerLastName"); 
        PIFirstName = request.getHeader("PIFirstName");
        PILastName = request.getHeader("PILastName");
        
        PIEmail = request.getHeader("PIEmail");
        
        submittingDepartment = request.getHeader("submittingDepartment");
        UGAPropNumber = request.getHeader("UGAPropNumber");
        CGResponsibleStaffEmail = request.getHeader("CGResponsibleStaffEmail");
        InvoicingContactEmail = request.getHeader("InvoicingContactEmail");
        OSPRSEmail = request.getHeader("OSPRSEmail");
        SponsorCode = request.getHeader("SponsorCode");
        PTLNumber = request.getHeader("PTLNumber");
        
        PrimaryContactEmail = request.getHeader("PrimaryContactEmail");
        SecondaryContactEmail = request.getHeader("SecondaryContactEmail");
        CGPrimaryContactEmail = request.getHeader("CGPrimaryContactEmail");
        CGSecondaryContactEmail = request.getHeader("CGSecondaryContactEmail");
        
        ptlExists = request.getHeader("ptlExists");
        CGRS = request.getHeader("CGRS");
        
        Selection = request.getHeader("Selection");
        DocTypeName = request.getHeader("DocTypeName");
        FileType = request.getHeader("FileType");
        
        expedite = request.getHeader("expedite");
        
        filetype_concat = request.getHeader("filetype_concat");
        
    }
    
    /*
        Methods to return the attribute values for an Object of this Class
    */
    
    public String getfiletype_concat()
    {
        return filetype_concat;
    }
    
    public String getExpedite()
    {
        return expedite;
    }
    
    public String getFileType()
    {
        return FileType;
    }
    
    public String getSelection()
    {
        return Selection;
    }
    
    public String getDocTypeName()
    {
        return DocTypeName;
    }
    
    public String getCGRS()
    {
        return CGRS;
    }
    
    public String getptlExists()
    {
        return ptlExists;
    }
    
    public String getSponsor()
    {
        return sponsor;
    }
    public String getOwnerFirstName()
    {
        return ownerFirstName;
    }
    public String getOwnerLastName()
    {
        return ownerLastName;
    }
    public String getPIFirstName()
    {
        return PIFirstName;
    }
    public String getPILastName()
    {
        return PILastName;
    }
    public String getPIEmail()
    {
        return PIEmail;
    }
    public String getsubmittingDepartment()
    {
        return submittingDepartment;
    }
    public String getUGAPropNumber()
    {
        return UGAPropNumber;
    }
    public String getCGResponsibleStaffEmail()
    {
        return CGResponsibleStaffEmail;
    }
    public String getInvoicingContactEmail()
    {
        return InvoicingContactEmail;
    }
    public String getOSPRSEmail()
    {
        return OSPRSEmail;
    }
    public String getSponsorCode()
    {
        return SponsorCode;
    }
    public String getPTLNumber()
    {
        return PTLNumber;
    }
    public String getPrimaryContactEmail()
    {
        return PrimaryContactEmail;
    }
    public String getSecondaryContactEmail()
    {
        return SecondaryContactEmail;
    }
    public String getCGPrimaryContactEmail()
    {
        return CGPrimaryContactEmail;
    }
    public String getCGSecondaryContactEmail()
    {
        return CGSecondaryContactEmail;
    }
}
