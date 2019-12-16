/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.converters.enums;

import javax.persistence.Converter;
import py.com.documenta.ntimovil.ejb.enums.EHBTipoCuenta;

/**
 *
 * @author alcides.alarcon
 */
@Converter
public class ConverterEHBTipoCuenta extends AbstractEnumConverter<EHBTipoCuenta> {

    @Override
    public String convertToDatabaseColumn(EHBTipoCuenta attribute) {
        return this.toDatabaseColumn(attribute);
    }

    @Override
    public EHBTipoCuenta convertToEntityAttribute(String dbData) {
        return this.toEntityAttribute(EHBTipoCuenta.class, dbData);
    }
}