package py.com.documenta.ntimovil.ejb.model.movil;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Motivovisita.class)
public abstract class Motivovisita_ {

	public static volatile SingularAttribute<Motivovisita, String> descripcion;
	public static volatile SingularAttribute<Motivovisita, Integer> idmotivovisita;
	public static volatile ListAttribute<Motivovisita, Visita> visitaList;
	public static volatile SingularAttribute<Motivovisita, Boolean> activo;

}

