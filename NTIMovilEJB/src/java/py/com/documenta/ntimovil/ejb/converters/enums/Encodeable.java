/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.converters.enums;

import java.util.stream.Stream;

/**
 *
 * @author alcides.alarcon
 */
public interface Encodeable {

    String getCodeBD();

    public static <E extends Enum<E> & Encodeable> E forToken(Class<E> cls, String tok) {
        final String t = tok.trim().toUpperCase();
        
        return Stream.of(cls.getEnumConstants())
                .filter(e -> e.getCodeBD().equals(t))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown code in bd '" +
                        tok + "' for enum " + cls.getName()));
    }
}
