/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.converters.enums;

import javax.persistence.Converter;
import py.com.documenta.ntimovil.ejb.enums.ETipoDestinoTicket;


/**
 *
 * @author alcides.alarcon
 */
@Converter
public class ConverterETipoDestinoTicket extends AbstractEnumConverter<ETipoDestinoTicket> {

    @Override
    public String convertToDatabaseColumn(ETipoDestinoTicket attribute) {
        return this.toDatabaseColumn(attribute);
    }

    @Override
    public ETipoDestinoTicket convertToEntityAttribute(String dbData) {
        return this.toEntityAttribute(ETipoDestinoTicket.class, dbData);
    }
}