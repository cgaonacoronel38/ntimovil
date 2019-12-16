package py.com.documenta.ntimovil.ejb.model.movil;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.documenta.ntimovil.ejb.enums.ETipoEstadoUsuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, Integer> diasvencpwd;
	public static volatile SingularAttribute<Usuario, Persona> persona;
	public static volatile SingularAttribute<Usuario, Date> ultimocambiopwd;
	public static volatile SingularAttribute<Usuario, Integer> intentosdeacceso;
	public static volatile SingularAttribute<Usuario, Date> fecbaja;
	public static volatile SingularAttribute<Usuario, ETipoEstadoUsuario> tipoestado;
	public static volatile SingularAttribute<Usuario, Integer> cantcambiopwd;
	public static volatile SingularAttribute<Usuario, Integer> idusuario;
	public static volatile SingularAttribute<Usuario, String> nrocel;
	public static volatile SingularAttribute<Usuario, Integer> audIdusuarioalta;
	public static volatile SingularAttribute<Usuario, Date> lastaccess;
	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile SingularAttribute<Usuario, Date> audFecalta;
	public static volatile SingularAttribute<Usuario, Date> audFecmodif;
	public static volatile SingularAttribute<Usuario, String> correo;
	public static volatile SingularAttribute<Usuario, Date> fechabloqueoacceso;
	public static volatile SingularAttribute<Usuario, Integer> audIdusuariomodif;
	public static volatile SingularAttribute<Usuario, String> username;
	public static volatile SingularAttribute<Usuario, Boolean> activo;
	public static volatile SingularAttribute<Usuario, BigInteger> idusuariodocumenta;

}

