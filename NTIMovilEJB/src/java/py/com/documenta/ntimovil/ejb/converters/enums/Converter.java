/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.converters.enums;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author desarrollo17
 */
public class Converter {
    
    public static BigDecimal castToBigDecimal(Object o) {
        try {
            return (BigDecimal) o;
        } catch (Exception e) {
        }

        try {
            return BigDecimal.valueOf(((BigInteger) o).longValue());
        } catch (Exception e) {
        }

        try {
            return BigDecimal.valueOf((Long) o);
        } catch (Exception e) {
        }
        try {
            return BigDecimal.valueOf((Double) o);
        } catch (Exception e) {
        }
        return BigDecimal.valueOf(((Integer) o).longValue());
    }
    
}
