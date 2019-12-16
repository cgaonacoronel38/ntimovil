package py.com.documenta.ntimovil.ejb.model.movil;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rastreo.class)
public abstract class Rastreo_ {

	public static volatile SingularAttribute<Rastreo, String> latitud;
	public static volatile SingularAttribute<Rastreo, String> longitud;
	public static volatile SingularAttribute<Rastreo, Long> idrastreo;
	public static volatile SingularAttribute<Rastreo, Integer> idusuario;
	public static volatile SingularAttribute<Rastreo, Date> fecharesgistro;

}

