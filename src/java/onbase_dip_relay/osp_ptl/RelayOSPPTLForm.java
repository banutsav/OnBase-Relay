package onbase_dip_relay.osp_ptl;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import onbase_dip_relay.Document;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 *
 * @author Glenn Owens
 */
public class RelayOSPPTLForm extends ValidatorForm implements java.io.Serializable {

    private ArrayList<Document> documents = new ArrayList();
    private String AccountNo = "";
    private String ACSCommentsToPI = "";
    private String AffirmFCOI = "";
    private String Agency = "";
    private String AgencyCode = "";
    private String AwardAmount = "";
    private String AwardNotifSent = "";
    private String Budget = "";
    private String BudgetBeginDate = "";
    private String BudgetEndDate = "";
    private String BudgetUnit = "";
    private String CCUID = "";
    private String CGBudgetBegDate = "";
    private String CGBudgetEndDate = "";
    private String CGPrimaryContact = "";
    private String CGPriConEmail = "";
    private String CGProjectBegDate = "";
    private String CGProjectEndDate = "";
    private String CGRespStaff = "";
    private String CGRSEmail = "";
    private String CGRSPhone = "";
    private String CGSecContact = "";
    private String CGSecContEmail = "";
    private String Dept = "";
    private String DocumentHandle = "";
    private String DueDate = "";
    private String DuplicatePTLNum = "";
    private String Expedite = "";
    private String FCOI = "";
    private String FCOITrain = "";
    private String InvoicingConEmail = "";
    private String LastUpdateDate = "";
    private String OSPRespGA = "";
    private String OSPRespStaff = "";
    private String OSPRSEmail = "";
    private String OSPRSPhone = "";
    private String OSPReviewLevel = "";
    private String PHSFlowthrough = "";
    private String PIEmail = "";
    private String PIFirstName = "";
    private String PILastName = "";
    private String PISpecificContact = "";
    private String PISpecConEmail = "";
    private String PrimaryContact = "";
    private String PrimaryContactEmail = "";
    private String ProjectBeginDate = "";
    private String ProjectEndDate = "";
    private String ProjectTitle = "";
    private String ProposalNum = "";
    private String ProposalDate = "";
    private String PTLFCOIAlert = "";
    private String QuickStatus = "";
    private String ReassignedTo = "";
    private String ReceiptDate = "";
    private String RFP = "";
    private String RoutePTLTo = "";
    private String SecondaryContact = "";
    private String SecondaryConEmail = "";
    private String Selection = "";
    private String Status = "";
    private String StatusCode = "";
    private String TempProposalNum = "";
    private String Transmittal = "";
    private String WFStatus = "";

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public Document getDocument(int index) {
        while (index >= documents.size()) {
            documents.add(new Document());
        }
        return documents.get(index);
    }

    public String getACSCommentsToPI() {
        return ACSCommentsToPI;
    }

    public void setACSCommentsToPI(String ACSCommentsToPI) {
        this.ACSCommentsToPI = ACSCommentsToPI;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    public String getAffirmFCOI() {
        return AffirmFCOI;
    }

    public void setAffirmFCOI(String AffirmFCOI) {
        this.AffirmFCOI = AffirmFCOI;
    }

    public String getAgency() {
        return Agency;
    }

    public void setAgency(String Agency) {
        this.Agency = Agency;
    }

    public String getAgencyCode() {
        return AgencyCode;
    }

    public void setAgencyCode(String AgencyCode) {
        this.AgencyCode = AgencyCode;
    }

    public String getAwardAmount() {
        return AwardAmount;
    }

    public void setAwardAmount(String AwardAmount) {
        this.AwardAmount = AwardAmount;
    }

    public String getAwardNotifSent() {
        return AwardNotifSent;
    }

    public void setAwardNotifSent(String AwardNotifSent) {
        this.AwardNotifSent = AwardNotifSent;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String Budget) {
        this.Budget = Budget;
    }

    public String getBudgetBeginDate() {
        return BudgetBeginDate;
    }

    public void setBudgetBeginDate(String BudgetBeginDate) {
        this.BudgetBeginDate = BudgetBeginDate;
    }

    public String getBudgetEndDate() {
        return BudgetEndDate;
    }

    public void setBudgetEndDate(String BudgetEndDate) {
        this.BudgetEndDate = BudgetEndDate;
    }

    public String getBudgetUnit() {
        return BudgetUnit;
    }

    public void setBudgetUnit(String BudgetUnit) {
        this.BudgetUnit = BudgetUnit;
    }

    public String getCCUID() {
        return CCUID;
    }

    public void setCCUID(String CCUID) {
        this.CCUID = CCUID;
    }

    public String getCGBudgetBegDate() {
        return CGBudgetBegDate;
    }

    public void setCGBudgetBegDate(String CGBudgetBegDate) {
        this.CGBudgetBegDate = CGBudgetBegDate;
    }

    public String getCGBudgetEndDate() {
        return CGBudgetEndDate;
    }

    public void setCGBudgetEndDate(String CGBudgetEndDate) {
        this.CGBudgetEndDate = CGBudgetEndDate;
    }

    public String getCGPriConEmail() {
        return CGPriConEmail;
    }

    public void setCGPriConEmail(String CGPriConEmail) {
        this.CGPriConEmail = CGPriConEmail;
    }

    public String getCGPrimaryContact() {
        return CGPrimaryContact;
    }

    public void setCGPrimaryContact(String CGPrimaryContact) {
        this.CGPrimaryContact = CGPrimaryContact;
    }

    public String getCGProjectBegDate() {
        return CGProjectBegDate;
    }

    public void setCGProjectBegDate(String CGProjectBegDate) {
        this.CGProjectBegDate = CGProjectBegDate;
    }

    public String getCGProjectEndDate() {
        return CGProjectEndDate;
    }

    public void setCGProjectEndDate(String CGProjectEndDate) {
        this.CGProjectEndDate = CGProjectEndDate;
    }

    public String getCGRSEmail() {
        return CGRSEmail;
    }

    public void setCGRSEmail(String CGRSEmail) {
        this.CGRSEmail = CGRSEmail;
    }

    public String getCGRSPhone() {
        return CGRSPhone;
    }

    public void setCGRSPhone(String CGRSPhone) {
        this.CGRSPhone = CGRSPhone;
    }

    public String getCGRespStaff() {
        return CGRespStaff;
    }

    public void setCGRespStaff(String CGRespStaff) {
        this.CGRespStaff = CGRespStaff;
    }

    public String getCGSecContEmail() {
        return CGSecContEmail;
    }

    public void setCGSecContEmail(String CGSecContEmail) {
        this.CGSecContEmail = CGSecContEmail;
    }

    public String getCGSecContact() {
        return CGSecContact;
    }

    public void setCGSecContact(String CGSecContact) {
        this.CGSecContact = CGSecContact;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String Dept) {
        this.Dept = Dept;
    }

    public String getDocumentHandle() {
        return DocumentHandle;
    }

    public void setDocumentHandle(String DocumentHandle) {
        this.DocumentHandle = DocumentHandle;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String DueDate) {
        this.DueDate = DueDate;
    }

    public String getDuplicatePTLNum() {
        return DuplicatePTLNum;
    }

    public void setDuplicatePTLNum(String DuplicatePTLNum) {
        this.DuplicatePTLNum = DuplicatePTLNum;
    }

    public String getExpedite() {
        return Expedite;
    }

    public void setExpedite(String Expedite) {
        this.Expedite = Expedite;
    }

    public String getFCOI() {
        return FCOI;
    }

    public void setFCOI(String FCOI) {
        this.FCOI = FCOI;
    }

    public String getFCOITrain() {
        return FCOITrain;
    }

    public void setFCOITrain(String FCOITrain) {
        this.FCOITrain = FCOITrain;
    }

    public String getInvoicingConEmail() {
        return InvoicingConEmail;
    }

    public void setInvoicingConEmail(String InvoicingConEmail) {
        this.InvoicingConEmail = InvoicingConEmail;
    }

    public String getLastUpdateDate() {
        return LastUpdateDate;
    }

    public void setLastUpdateDate(String LastUpdateDate) {
        this.LastUpdateDate = LastUpdateDate;
    }

    public String getOSPRSEmail() {
        return OSPRSEmail;
    }

    public void setOSPRSEmail(String OSPRSEmail) {
        this.OSPRSEmail = OSPRSEmail;
    }

    public String getOSPRSPhone() {
        return OSPRSPhone;
    }

    public void setOSPRSPhone(String OSPRSPhone) {
        this.OSPRSPhone = OSPRSPhone;
    }

    public String getOSPRespGA() {
        return OSPRespGA;
    }

    public void setOSPRespGA(String OSPRespGA) {
        this.OSPRespGA = OSPRespGA;
    }

    public String getOSPRespStaff() {
        return OSPRespStaff;
    }

    public void setOSPRespStaff(String OSPRespStaff) {
        this.OSPRespStaff = OSPRespStaff;
    }

    public String getOSPReviewLevel() {
        return OSPReviewLevel;
    }

    public void setOSPReviewLevel(String OSPReviewLevel) {
        this.OSPReviewLevel = OSPReviewLevel;
    }

    public String getPHSFlowthrough() {
        return PHSFlowthrough;
    }

    public void setPHSFlowthrough(String PHSFlowthrough) {
        this.PHSFlowthrough = PHSFlowthrough;
    }

    public String getPIEmail() {
        return PIEmail;
    }

    public void setPIEmail(String PIEmail) {
        this.PIEmail = PIEmail;
    }

    public String getPIFirstName() {
        return PIFirstName;
    }

    public void setPIFirstName(String PIFirstName) {
        this.PIFirstName = PIFirstName;
    }

    public String getPILastName() {
        return PILastName;
    }

    public void setPILastName(String PILastName) {
        this.PILastName = PILastName;
    }

    public String getPISpecConEmail() {
        return PISpecConEmail;
    }

    public void setPISpecConEmail(String PISpecConEmail) {
        this.PISpecConEmail = PISpecConEmail;
    }

    public String getPISpecificContact() {
        return PISpecificContact;
    }

    public void setPISpecificContact(String PISpecificContact) {
        this.PISpecificContact = PISpecificContact;
    }

    public String getPTLFCOIAlert() {
        return PTLFCOIAlert;
    }

    public void setPTLFCOIAlert(String PTLFCOIAlert) {
        this.PTLFCOIAlert = PTLFCOIAlert;
    }

    public String getPrimaryContact() {
        return PrimaryContact;
    }

    public void setPrimaryContact(String PrimaryContact) {
        this.PrimaryContact = PrimaryContact;
    }

    public String getPrimaryContactEmail() {
        return PrimaryContactEmail;
    }

    public void setPrimaryContactEmail(String PrimaryContactEmail) {
        this.PrimaryContactEmail = PrimaryContactEmail;
    }

    public String getProjectBeginDate() {
        return ProjectBeginDate;
    }

    public void setProjectBeginDate(String ProjectBeginDate) {
        this.ProjectBeginDate = ProjectBeginDate;
    }

    public String getProjectEndDate() {
        return ProjectEndDate;
    }

    public void setProjectEndDate(String ProjectEndDate) {
        this.ProjectEndDate = ProjectEndDate;
    }

    public String getProjectTitle() {
        return ProjectTitle;
    }

    public void setProjectTitle(String ProjectTitle) {
        this.ProjectTitle = ProjectTitle;
    }

    public String getProposalDate() {
        return ProposalDate;
    }

    public void setProposalDate(String ProposalDate) {
        this.ProposalDate = ProposalDate;
    }

    public String getProposalNum() {
        return ProposalNum;
    }

    public void setProposalNum(String ProposalNum) {
        this.ProposalNum = ProposalNum;
    }

    public String getQuickStatus() {
        return QuickStatus;
    }

    public void setQuickStatus(String QuickStatus) {
        this.QuickStatus = QuickStatus;
    }

    public String getRFP() {
        return RFP;
    }

    public void setRFP(String RFP) {
        this.RFP = RFP;
    }

    public String getReassignedTo() {
        return ReassignedTo;
    }

    public void setReassignedTo(String ReassignedTo) {
        this.ReassignedTo = ReassignedTo;
    }

    public String getReceiptDate() {
        return ReceiptDate;
    }

    public void setReceiptDate(String ReceiptDate) {
        this.ReceiptDate = ReceiptDate;
    }

    public String getRoutePTLTo() {
        return RoutePTLTo;
    }

    public void setRoutePTLTo(String RoutePTLTo) {
        this.RoutePTLTo = RoutePTLTo;
    }

    public String getSecondaryConEmail() {
        return SecondaryConEmail;
    }

    public void setSecondaryConEmail(String SecondaryConEmail) {
        this.SecondaryConEmail = SecondaryConEmail;
    }

    public String getSecondaryContact() {
        return SecondaryContact;
    }

    public void setSecondaryContact(String SecondaryContact) {
        this.SecondaryContact = SecondaryContact;
    }

    public String getSelection() {
        return Selection;
    }

    public void setSelection(String Selection) {
        this.Selection = Selection;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getTempProposalNum() {
        return TempProposalNum;
    }

    public void setTempProposalNum(String TempProposalNum) {
        this.TempProposalNum = TempProposalNum;
    }

    public String getTransmittal() {
        return Transmittal;
    }

    public void setTransmittal(String Transmittal) {
        this.Transmittal = Transmittal;
    }

    public String getWFStatus() {
        return WFStatus;
    }

    public void setWFStatus(String WFStatus) {
        this.WFStatus = WFStatus;
    }
}