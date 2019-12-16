package py.com.documenta.ntimovil.ejb.model.movil;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Menu.class)
public abstract class Menu_ {

	public static volatile SingularAttribute<Menu, String> descripcion;
	public static volatile ListAttribute<Menu, Permisorol> permisorolList;
	public static volatile SingularAttribute<Menu, Integer> idmenu;
	public static volatile SingularAttribute<Menu, String> url;
	public static volatile SingularAttribute<Menu, Boolean> activo;

}

