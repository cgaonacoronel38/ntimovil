package py.com.documenta.ntimovil.ejb.model.movil;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Empresa.class)
public abstract class Empresa_ {

	public static volatile SingularAttribute<Empresa, String> descripcion;
	public static volatile SingularAttribute<Empresa, String> ruc;
	public static volatile SingularAttribute<Empresa, String> rucrepresentantelegal;
	public static volatile ListAttribute<Empresa, Persona> personaList;
	public static volatile SingularAttribute<Empresa, String> direccion;
	public static volatile SingularAttribute<Empresa, String> representantelegal;
	public static volatile SingularAttribute<Empresa, Integer> audIdusuarioalta;
	public static volatile SingularAttribute<Empresa, Integer> idempresa;
	public static volatile SingularAttribute<Empresa, Date> audFecalta;
	public static volatile SingularAttribute<Empresa, Date> audFecmodif;
	public static volatile SingularAttribute<Empresa, String> razonsocial;
	public static volatile SingularAttribute<Empresa, String> celular;
	public static volatile SingularAttribute<Empresa, String> localidad;
	public static volatile SingularAttribute<Empresa, Integer> audIdusuariomodif;
	public static volatile SingularAttribute<Empresa, String> telefono;
	public static volatile SingularAttribute<Empresa, Boolean> activo;

}

