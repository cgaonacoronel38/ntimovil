

package py.com.documenta.ntimovil.ejb.bean.setup;

/**
 *
 * @author francisco
 */
public class EmailTemplateSetup {
    private String recoveryTitleHeader;
    private String recoveryTitle1;
    private String recoveryText;
    private String year;
    private String emailJefeSAU;

    public String getEmailJefeSAU() {
        return emailJefeSAU;
    }

    public void setEmailJefeSAU(String emailJefeSAU) {
        this.emailJefeSAU = emailJefeSAU;
    }
    
    public EmailTemplateSetup() {
    }

    public String getRecoveryTitleHeader() {
        return recoveryTitleHeader;
    }

    public void setRecoveryTitleHeader(String recoveryTitleHeader) {
        this.recoveryTitleHeader = recoveryTitleHeader;
    }

    public String getRecoveryTitle1() {
        return recoveryTitle1;
    }

    public void setRecoveryTitle1(String recoveryTitle1) {
        this.recoveryTitle1 = recoveryTitle1;
    }

    public String getRecoveryText() {
        return recoveryText;
    }

    public void setRecoveryText(String recoveryText) {
        this.recoveryText = recoveryText;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
   
}

