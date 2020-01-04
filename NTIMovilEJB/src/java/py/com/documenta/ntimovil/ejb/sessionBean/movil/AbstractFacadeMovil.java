package py.com.documenta.ntimovil.ejb.sessionBean.movil;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tid
 */
public abstract class AbstractFacadeMovil<T> {
    
    protected final Logger log = LoggerFactory.getLogger(AbstractFacadeMovil.class);
    
    @PersistenceContext(unitName = "NTIMovilEJBPU")
    private EntityManager emMaster;
    
    @PersistenceContext(unitName = "NTIMovilEJBPU-Slave")
    private EntityManager emSlave;
    
    private Class<T> entityClass;
    
    
    public AbstractFacadeMovil(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected EntityManager getEntityManager() {
        if (emMaster == null) {
            log.error("El/los entitymanagers no pudieron crearse.");
            return null;
        }
        return emMaster;
    }
    
    public EntityManager getSlaveEntityManager(){
        if (emMaster == null && emSlave == null) {
            log.error("El/los entitymanagers no pudieron crearse.");
            
            return null;
        }
        return (emSlave != null) ? emSlave : emMaster;
    }
    
    public void create(T entity) throws ConstraintViolationException {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }
    
    public void edit(T entity) throws ConstraintViolationException {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }
    
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
    }
    
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public void refresh(T entity) throws ConstraintViolationException{
        getEntityManager().refresh(entity);
    }

    /**
     * Lista las tuplas que tienen el campo activo = true y se ordena por el
     * campo descripcion si lo tuviera.
     *
     * @return
     * @throws NandutiEJBException
     */
    public List<T> findAllByActiveGeneric() throws Exception {
        return findAllByActiveGeneric(true, null);
    }

    /**
     * Crea la sentencia SQL a través de CriteriaBuildder agregando el filtro
     * por el campo activo, qeu deberían contener las tablas maestras y se puede
     * incluir campos de orden.
     *
     * @param onlyActive Si es TRUE el resultado incluye solo las tuplas con el
     * valor activo = true.
     * @param fieldsOrderBy Lista de campos para ordenar el reaultado final.
     * @return
     * @throws NandutiEJBException
     */
    public List<T> findAllByActiveGeneric(boolean onlyActive, String[] fieldsOrderBy) throws Exception {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery q = cb.createQuery(entityClass);
            Root<T> root = q.from(entityClass);
            q.select(root);
            
            Path<Boolean> isActive = root.get("activo");
            //Expression<Boolean> paramOnlyActive = cb.parameter(Boolean.class);
            //q.where(cb.isTrue(isActive), cb.and(cb.isTrue(paramOnlyActive)));

            if (isActive == null) {
                throw new Exception("El campo Activo NO existe en la entidad " + entityClass.getName());
            }
            
            if (onlyActive) {
                q.where(cb.isTrue(isActive));
            }

            // Resolviendo el order by por varios campos, todos ascendentes :)
            List<Order> orders = null;
            if (fieldsOrderBy != null) {
                for (int i = 0; i < fieldsOrderBy.length; i++) {
                    if (fieldsOrderBy[i] != null && !fieldsOrderBy[i].trim().isEmpty()) {
                        if (i == 0) {
                            orders = new ArrayList<Order>();
                        }
                        
                        if (root.get(fieldsOrderBy[i]) != null) {
                            orders.add(cb.asc(root.get(fieldsOrderBy[i])));
                        } else {
                            log.warn("El campo {} no es atributo de la entidad {}, no se incluye en el order by.",
                                    fieldsOrderBy[i],
                                    entityClass.getSimpleName());
                        }
                    }
                }
            } else {
                // Se agrega el order by por descripción si hubiess
                Expression<String> desc = findDescriptionField(root);
                
                if (desc != null) {
                    orders.add(cb.asc(desc));
                }
            }
            
            if (orders != null && orders.size() > 0) {
                q.orderBy(orders);
            }
            
            return getEntityManager().createQuery(q).getResultList();
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    private Expression<String> findDescriptionField(Root<T> root) {
        Expression<String> descField = root.get("description");
        
        if (descField == null) {
            descField = root.get("descripcion");
        }
        
        return descField;
    }
    
    public List<T> findAll() {
        return findAll(null);
    }
    
    public List<T> findAll(String[] fieldsOrderBy) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery q = cb.createQuery();
        Root<T> root = q.from(entityClass);
        q.select(root);

        // Resolviendo el order by por varios campos, todos ascendentes :)
        List<Order> orders = null;
        if (fieldsOrderBy != null) {
            for (int i = 0; i < fieldsOrderBy.length; i++) {
                if (fieldsOrderBy[i] != null && !fieldsOrderBy[i].trim().isEmpty()) {
                    if (i == 0) {
                        orders = new ArrayList<Order>();
                    }
                    if (root.get(fieldsOrderBy[i]) != null) {
                        orders.add(cb.asc(root.get(fieldsOrderBy[i])));
                    } else {
                        log.warn("El campo {} no es atributo de la entidad {}, no se incluye en el order by.",
                                fieldsOrderBy[i],
                                entityClass.getSimpleName());
                    }
                }
            }
        }
        
        if (orders != null && orders.size() > 0) {
            q.orderBy(orders);
        }
        
        return getEntityManager().createQuery(q).getResultList();
    }
    
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        
        return q.getResultList();
    }
    
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public void detached(T t) {
        getEntityManager().detach(t);
    }
}