/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.converters.enums;

import javax.persistence.Converter;
import py.com.documenta.ntimovil.ejb.enums.ETipoEstadoServicio;


/**
 *
 * @author alcides.alarcon
 */
@Converter
public class ConverterETipoEstadoServicio extends AbstractEnumConverter<ETipoEstadoServicio> {

    @Override
    public String convertToDatabaseColumn(ETipoEstadoServicio attribute) {
        return this.toDatabaseColumn(attribute);
    }

    @Override
    public ETipoEstadoServicio convertToEntityAttribute(String dbData) {
        return this.toEntityAttribute(ETipoEstadoServicio.class, dbData);
    }
}