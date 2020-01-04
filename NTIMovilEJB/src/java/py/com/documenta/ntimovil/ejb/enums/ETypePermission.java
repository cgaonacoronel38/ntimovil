/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.enums;


/**
 *
 * @author alcides.alarcon
 */
public enum ETypePermission {
    NO_ACCESS(0, "SIN ACCESO"),
    VISUALIZE(1, "VISUALIZAR VISTA"),
    ADD(2, "AGREGAR REGISTRO"),
    MODIFY(4, "MODIFICAR REGISTRO"),
    DELETE(8, "BORRAR REGISTRO"),
    ANNULAR(16, "ANULAR REGISTRO"),
    SEARCH(32, "BUSCAR REGISTROS"),
    PRINT(64, "IMPRIMIR REGISTRO"),
    OBJECT_VISUALIZE(128, "VISUALIZAR OBJETO"),
    OBJECT_EDIT(256, "EDITAR OBJETO"),
    DOWN_REPORT(512, "DESCARGAR REPORTE"),
    PARAM_EDIT_REPORT(1024, "EDITAR FILTROS DEL REPORTE"),
    ADD_TO_FAVORITES(2048, "AGREGAR A MENU FAVORITOS"),
    ALL_PERMISSION(1048575, "PERMISO TOTAL");
    
    private final int value;
    private final String obs;
    
    private ETypePermission(int value, String obs) {
        this.value = value;
        this.obs = obs;
    }

    public int getValue() {
        return value;
    }
    
    public String getObs() {
        return obs;
    }
}
