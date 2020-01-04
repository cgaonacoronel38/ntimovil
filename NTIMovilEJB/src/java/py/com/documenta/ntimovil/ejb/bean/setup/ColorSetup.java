/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.bean.setup;

/**
 *
 * @author alcides.alarcon
 */
public class ColorSetup {

    private String name;
    private String hexaColor;
    private String materialDesign;
    private String banner;
    private String logo;
    private String logoBackGroud;
    private String textColor;
    private String extraColor;
    private String materialDesignClassSecondary;
    private String hexaColorSecondary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHexaColor() {
        return hexaColor;
    }

    public void setHexaColor(String hexaColor) {
        this.hexaColor = hexaColor;
    }

    public String getMaterialDesign() {
        return materialDesign;
    }

    public void setMaterialDesign(String materialDesign) {
        this.materialDesign = materialDesign;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public String getLogoBackGroud() {
        return logoBackGroud;
    }

    public void setLogoBackGroud(String logoBackGroud) {
        this.logoBackGroud = logoBackGroud;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getExtraColor() {
        return extraColor;
    }

    public void setExtraColor(String extraColor) {
        this.extraColor = extraColor;
    }

    public String getMaterialDesignClassSecondary() {
        return materialDesignClassSecondary;
    }

    public void setMaterialDesignClassSecondary(String materialDesignClassSecondary) {
        this.materialDesignClassSecondary = materialDesignClassSecondary;
    }

    public String getHexaColorSecondary() {
        return hexaColorSecondary;
    }

    public void setHexaColorSecondary(String hexaColorSecondary) {
        this.hexaColorSecondary = hexaColorSecondary;
    }
}
