/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.converters.enums;

import javax.persistence.Converter;
import py.com.documenta.ntimovil.ejb.enums.ETipoPago;


/**
 *
 * @author alcides.alarcon
 */
@Converter
public class ConverterETipoPago extends AbstractEnumConverter<ETipoPago> {

    @Override
    public String convertToDatabaseColumn(ETipoPago attribute) {
        return this.toDatabaseColumn(attribute);
    }

    @Override
    public ETipoPago convertToEntityAttribute(String dbData) {
        return this.toEntityAttribute(ETipoPago.class, dbData);
    }
}