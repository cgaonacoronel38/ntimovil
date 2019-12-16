/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.bean.setup;

/**
 *
 * @author eduardo
 */
public class ServerWSocketSetup {

    private String contextLink;
    private String separatorLinkField;
    private int valueToMultiplyIdUser;
    private int linkHoursValidity;

    public String getContextLink() {
        return contextLink;
    }

    public void setContextLink(String contextLink) {
        this.contextLink = contextLink;
    }

    public String getSeparatorLinkField() {
        return separatorLinkField;
    }

    public void setSeparatorLinkField(String separatorLinkField) {
        this.separatorLinkField = separatorLinkField;
    }

    public int getValueToMultiplyIdUser() {
        return valueToMultiplyIdUser;
    }

    public void setValueToMultiplyIdUser(int valueToMultiplyIdUser) {
        this.valueToMultiplyIdUser = valueToMultiplyIdUser;
    }

    public int getLinkHoursValidity() {
        return linkHoursValidity;
    }

    public void setLinkHoursValidity(int linkHoursValidity) {
        this.linkHoursValidity = linkHoursValidity;
    }

}
