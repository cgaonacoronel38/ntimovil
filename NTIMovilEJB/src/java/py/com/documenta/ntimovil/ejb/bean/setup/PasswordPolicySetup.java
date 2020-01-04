

package py.com.documenta.ntimovil.ejb.bean.setup;

/**
 *
 * @author francisco
 */
public class PasswordPolicySetup {
    private Integer pwdAttemptsAllowed;
    private Integer blockHours;
    private Boolean allowRecoveryWhenIsBlocked;
    private Integer daysPasswordTemporaryExpiration;

    public Integer getPwdAttemptsAllowed() {
        return pwdAttemptsAllowed;
    }

    public void setPwdAttemptsAllowed(Integer pwdAttemptsAllowed) {
        this.pwdAttemptsAllowed = pwdAttemptsAllowed;
    }

    public Integer getBlockHours() {
        return blockHours;
    }

    public Integer getDaysPasswordTemporaryExpiration() {
        return daysPasswordTemporaryExpiration;
    }

    public void setDaysPasswordTemporaryExpiration(Integer daysPasswordTemporaryExpiration) {
        this.daysPasswordTemporaryExpiration = daysPasswordTemporaryExpiration;
    }

    public Boolean getAllowRecoveryWhenIsBlocked() {
        return allowRecoveryWhenIsBlocked;
    }

    public void setAllowRecoveryWhenIsBlocked(Boolean allowRecoveryWhenIsBlocked) {
        this.allowRecoveryWhenIsBlocked = allowRecoveryWhenIsBlocked;
    }

    public void setBlockHours(Integer blockHours) {
        this.blockHours = blockHours;
    }

    public PasswordPolicySetup() {
    }  
}