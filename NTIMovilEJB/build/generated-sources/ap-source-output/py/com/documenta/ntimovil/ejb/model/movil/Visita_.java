package py.com.documenta.ntimovil.ejb.model.movil;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Visita.class)
public abstract class Visita_ {

	public static volatile SingularAttribute<Visita, Integer> idusuariovisitador;
	public static volatile SingularAttribute<Visita, String> latitud;
	public static volatile SingularAttribute<Visita, Motivovisita> idmotivovisita;
	public static volatile SingularAttribute<Visita, Integer> idusuarioregistro;
	public static volatile SingularAttribute<Visita, String> documento;
	public static volatile SingularAttribute<Visita, Date> fechavisita;
	public static volatile SingularAttribute<Visita, Date> fecharegistro;
	public static volatile SingularAttribute<Visita, String> cliente;
	public static volatile SingularAttribute<Visita, Long> idvisita;
	public static volatile SingularAttribute<Visita, String> longitud;
	public static volatile SingularAttribute<Visita, Boolean> visitado;
	public static volatile SingularAttribute<Visita, String> observaciones;
	public static volatile SingularAttribute<Visita, Integer> usuarioactualizacion;
	public static volatile SingularAttribute<Visita, Date> fechaactualizacion;

}

