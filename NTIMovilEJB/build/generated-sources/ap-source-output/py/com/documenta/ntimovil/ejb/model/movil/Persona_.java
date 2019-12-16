package py.com.documenta.ntimovil.ejb.model.movil;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Persona.class)
public abstract class Persona_ {

	public static volatile SingularAttribute<Persona, Date> fecnac;
	public static volatile SingularAttribute<Persona, String> obs;
	public static volatile SingularAttribute<Persona, String> nrodoc;
	public static volatile ListAttribute<Persona, Usuario> usuarioList;
	public static volatile SingularAttribute<Persona, String> apellido;
	public static volatile SingularAttribute<Persona, Integer> idtipodoc;
	public static volatile SingularAttribute<Persona, Empresa> empresa;
	public static volatile SingularAttribute<Persona, PersonaPK> personaPK;
	public static volatile SingularAttribute<Persona, String> nombre;
	public static volatile SingularAttribute<Persona, Boolean> activo;

}

