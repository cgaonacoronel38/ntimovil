package py.com.documenta.ntimovil.ejb.model.movil;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rol.class)
public abstract class Rol_ {

	public static volatile SingularAttribute<Rol, String> descripcion;
	public static volatile SingularAttribute<Rol, Integer> idrol;
	public static volatile ListAttribute<Rol, Permisorol> permisorolList;
	public static volatile SingularAttribute<Rol, Boolean> sistema;
	public static volatile SingularAttribute<Rol, String> nota;
	public static volatile SingularAttribute<Rol, String> gruporealm;
	public static volatile SingularAttribute<Rol, Boolean> activo;
	public static volatile ListAttribute<Rol, Usuariorol> usuariorolList;

}

