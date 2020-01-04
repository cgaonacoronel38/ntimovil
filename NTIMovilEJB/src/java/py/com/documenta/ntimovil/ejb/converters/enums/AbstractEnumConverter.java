/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.converters.enums;

import javax.persistence.AttributeConverter;

/**
 * @author alcides.alarcon
 * @param <E>
 */
public abstract class AbstractEnumConverter<E extends Enum<E> & Encodeable>
            implements AttributeConverter<E, String> {

    public String toDatabaseColumn(E attr) {
        return (attr == null)
                ? null
                : attr.getCodeBD();
    }

    public E toEntityAttribute(Class<E> cls, String dbCol) {
        return (dbCol == null)
                ? null
                : Encodeable.forToken(cls, dbCol);
    }
}