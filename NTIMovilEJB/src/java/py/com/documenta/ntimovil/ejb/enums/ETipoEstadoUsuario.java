/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.enums;

import py.com.documenta.ntimovil.ejb.converters.enums.Encodeable;

/**
 *
 * @author eduardo
 */
public enum ETipoEstadoUsuario implements Encodeable {
    BLOCKED_UNDEFINED("BLOCKED_UNDEFINED"),
    BLOCKED_BY_FAILED_ATTEMPTS("BLOCKED_BY_FAILED_ATTEMPTS"),
    BLOCKED_BY_PASSWORD_EXPIRED("BLOCKED_BY_PASSWORD_EXPIRED"),
    FORGOT_PASSWORD("FORGOT_PASSWORD"),
    NEW_REGISTERED("NEW_REGISTERED"),
    MANDATORY_CHANGE("MANDATORY_CHANGE"),
    ACTIVE("ACTIVE"),
    FINAL_BLOCK("FINAL_BLOCK");

    private final String codeBD;

    private ETipoEstadoUsuario(String codeBD) {
        this.codeBD = codeBD;
    }

    public String getCodeBD() {
        return codeBD;
    }

    public static ETipoEstadoUsuario valueOfByCodeBD(String codeBD) {
        if (codeBD.equalsIgnoreCase("BLOCKED_UNDEFINED")) {
            return ETipoEstadoUsuario.BLOCKED_UNDEFINED;
        }

        if (codeBD.equalsIgnoreCase("BLOCKED_BY_FAILED_ATTEMPTS")) {
            return ETipoEstadoUsuario.BLOCKED_BY_FAILED_ATTEMPTS;
        }

        if (codeBD.equalsIgnoreCase("BLOCKED_BY_PASSWORD_EXPIRED")) {
            return ETipoEstadoUsuario.BLOCKED_BY_PASSWORD_EXPIRED;
        }

        if (codeBD.equalsIgnoreCase("FORGOT_PASSWORD")) {
            return ETipoEstadoUsuario.FORGOT_PASSWORD;
        }

        if (codeBD.equalsIgnoreCase("NEW_REGISTERED")) {
            return ETipoEstadoUsuario.NEW_REGISTERED;
        }

        if (codeBD.equalsIgnoreCase("MANDATORY_CHANGE")) {
            return ETipoEstadoUsuario.MANDATORY_CHANGE;
        }

        if (codeBD.equalsIgnoreCase("ACTIVE")) {
            return ETipoEstadoUsuario.ACTIVE;
        }

        if (codeBD.equalsIgnoreCase("FINAL_BLOCK")) {
            return ETipoEstadoUsuario.FINAL_BLOCK;
        }

        throw new RuntimeException(String.format("El codebd [%s] no es válido.", codeBD));
    }
}
