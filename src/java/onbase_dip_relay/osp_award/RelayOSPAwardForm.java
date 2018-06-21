package onbase_dip_relay.osp_award;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import onbase_dip_relay.Document;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 *
 * @author Glenn Owens
 */
public class RelayOSPAwardForm extends ValidatorForm implements java.io.Serializable {

    private ArrayList<Document> documents = new ArrayList();
    private String proposalNum = "";
    private String tempProposalNum = "";
    private String accountNum = "";
    private String budgetUnit = "";
    private String description = "";
    private String piFirstName = "";
    private String piLastName = "";
    private String status = "";

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

    public String getProposalNum() {
        return proposalNum;
    }

    public void setProposalNum(String proposalNum) {
        this.proposalNum = proposalNum;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getBudgetUnit() {
        return budgetUnit;
    }

    public void setBudgetUnit(String budgetUnit) {
        this.budgetUnit = budgetUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPiFirstName() {
        return piFirstName;
    }

    public void setPiFirstName(String piFirstName) {
        this.piFirstName = piFirstName;
    }

    public String getPiLastName() {
        return piLastName;
    }

    public void setPiLastName(String piLastName) {
        this.piLastName = piLastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTempProposalNum() {
        return tempProposalNum;
    }

    public void setTempProposalNum(String tempProposalNum) {
        this.tempProposalNum = tempProposalNum;
    }
}