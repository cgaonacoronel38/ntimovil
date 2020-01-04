/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.converters.enums;


import javax.persistence.Converter;
import py.com.documenta.ntimovil.ejb.enums.ETipoEstadoUsuario;

/**
 *
 * @author alcides.alarcon
 */
@Converter
public class ConverterETipoEstadoUsuario extends AbstractEnumConverter<ETipoEstadoUsuario> {

    @Override
    public String convertToDatabaseColumn(ETipoEstadoUsuario attribute) {
        return this.toDatabaseColumn(attribute);
    }

    @Override
    public ETipoEstadoUsuario convertToEntityAttribute(String dbData) {
        return this.toEntityAttribute(ETipoEstadoUsuario.class, dbData);
    }
}