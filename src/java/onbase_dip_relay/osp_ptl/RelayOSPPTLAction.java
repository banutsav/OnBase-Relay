package onbase_dip_relay.osp_ptl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

/**
 *
 * @author Glenn Owens
 */
public class RelayOSPPTLAction extends Action {

    private static final String docTypeName = "OSP - PTL (eform)";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            final RelayOSPPTLForm c_form = (RelayOSPPTLForm) form;

            final Properties emailProps = new Properties();
            emailProps.load(request.getSession().getServletContext().getResourceAsStream("/WEB-INF/ftp.properties"));
            final String ftpHost = emailProps.getProperty("ftpHost");
            final int ftpPort = Integer.valueOf(emailProps.getProperty("ftpPort"));
            final String ftpUsername = emailProps.getProperty("ftpUsername");
            final String ftpPassword = emailProps.getProperty("ftpPassword");
            final String ftpRootFolder = emailProps.getProperty("ftpRootFolder");

            final ArrayList<byte[]> fileDataList = new ArrayList();

            String importIndexFileText = "";

            final String identifier = c_form.getProposalNum();
            final String iifFileName = "IIF_" + identifier + ".dip";
            final String docFolder = "DF_" + identifier;

            for (int i = 0; i < c_form.getDocuments().size(); i++) {
                InputStream in = null;
                try {
                    //Process Form File
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

                    importIndexFileText += "BEGIN:";
                    importIndexFileText += "\r\n";
                    importIndexFileText += "DocTypeName: " + docTypeName;
                    importIndexFileText += "\r\n";
                    importIndexFileText += "FullPath: " + ftpRootFolder + "\\" + docTypeName + "\\" + docFolder + "\\" + c_form.getDocument(i).getFileName();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Account No: " + c_form.getAccountNo();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "ACSCommentsToPI: " + c_form.getACSCommentsToPI();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "AffirmFCOI: " + c_form.getAffirmFCOI();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Agency: " + c_form.getAgency();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Agency Code: " + c_form.getAgencyCode();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Award Amount: " + c_form.getAwardAmount();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Award Notif Sent: " + c_form.getAwardNotifSent();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Budget: " + c_form.getBudget();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Budget Begin Date: " + c_form.getBudgetBeginDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Budget End Date: " + c_form.getBudgetEndDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Budget Unit: " + c_form.getBudgetUnit();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CCUID: " + c_form.getCCUID();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG Budget Beg Date: " + c_form.getCGBudgetBegDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG Budget End Date: " + c_form.getCGBudgetEndDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG Primary Contact: " + c_form.getCGPrimaryContact();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG Pri Con Email: " + c_form.getCGPriConEmail();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG Project Beg Date: " + c_form.getCGProjectBegDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG Project End Date: " + c_form.getCGProjectEndDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG Resp Staff: " + c_form.getCGRespStaff();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG RS Email: " + c_form.getCGRSEmail();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG RS Phone: " + c_form.getCGRSPhone();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG Sec Contact: " + c_form.getCGSecContact();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "CG Sec Cont Email: " + c_form.getCGSecContEmail();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Dept: " + c_form.getDept();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Document Handle: " + c_form.getDocumentHandle();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Due Date: " + c_form.getDueDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Duplicate PTL #: " + c_form.getDuplicatePTLNum();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Expedite: " + c_form.getExpedite();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "FCOI: " + c_form.getFCOI();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "FCOITrain: " + c_form.getFCOITrain();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Invoicing Con Email: " + c_form.getInvoicingConEmail();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "LAST UPDATE DATE: " + c_form.getLastUpdateDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "OSP Resp GA: " + c_form.getOSPRespGA();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "OSP Resp Staff: " + c_form.getOSPRespStaff();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "OSP RS Email: " + c_form.getOSPRSEmail();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "OSP RS Phone: " + c_form.getOSPRSPhone();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "OSPReviewLevel: " + c_form.getOSPReviewLevel();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "PHS Flowthrough: " + c_form.getPHSFlowthrough();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "PI Email: " + c_form.getPIEmail();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "PI First Name: " + c_form.getPIFirstName();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "PI Last Name: " + c_form.getPILastName();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "PI Specific Contact: " + c_form.getPISpecificContact();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "PI Spec Con Email: " + c_form.getPISpecConEmail();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Primary Contact: " + c_form.getPrimaryContact();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Primary Contact Email: " + c_form.getPrimaryContactEmail();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Project Begin Date: " + c_form.getProjectBeginDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Project End Date: " + c_form.getProjectEndDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Project Title: " + c_form.getProjectTitle();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Proposal #: " + c_form.getProposalNum();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Proposal Date: " + c_form.getProposalDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "PTL FCOI Alert: " + c_form.getPTLFCOIAlert();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Quick Status: " + c_form.getQuickStatus();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Reassigned To: " + c_form.getReassignedTo();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "ReceiptDate: " + c_form.getReceiptDate();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "RFP: " + c_form.getRFP();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Route PTL To: " + c_form.getRoutePTLTo();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Secondary Contact: " + c_form.getSecondaryContact();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Secondary Con Email: " + c_form.getSecondaryConEmail();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Selection: " + c_form.getSelection();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Status: " + c_form.getStatus();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Status Code: " + c_form.getStatusCode();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Temp Proposal #: " + c_form.getTempProposalNum();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "Transmittal: " + c_form.getTransmittal();
                    importIndexFileText += "\r\n";
                    importIndexFileText += "WFStatus: " + c_form.getWFStatus();
                    importIndexFileText += "\r\n";
                } catch (Exception ex) {
                    throw ex;
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
            }

            importIndexFileText += "END OF FILE";

            //Do FTP Transfer
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
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                FTPUtils.removeDirectory(ftpClient, docTypeName + "/" + docFolder, "");
                ftpClient.makeDirectory(docTypeName + "/" + docFolder);

                for (int i = 0; i < fileDataList.size(); i++) {
                    ftpClient.storeFile(docTypeName + "/" + docFolder + "/" + c_form.getDocument(i).getFileName(), new ByteArrayInputStream(fileDataList.get(i)));
                }
                ftpClient.storeFile(docTypeName + "/" + iifFileName, new ByteArrayInputStream(importIndexFileText.getBytes()));
            } catch (Exception ex) {
                throw ex;
            } finally {
                if (ftpClient != null) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            }

            return null;
        } catch (Exception ex) {
            servlet.log("Error message", ex);
            throw ex;//return a Tomcat Error page with exception stacktrace
            //return mapping.findForward("gfwd_error");
        }
    }
}