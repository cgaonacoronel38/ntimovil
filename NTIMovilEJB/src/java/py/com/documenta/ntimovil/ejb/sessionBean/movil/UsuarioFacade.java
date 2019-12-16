/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.sessionBean.movil;

import com.documenta.util.encript.AESSymetricCrypto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.ejb.enums.ETipoEstadoUsuario;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;

/**
 *
 * @author TID01
 */
@Stateless
public class UsuarioFacade extends AbstractFacadeMovil<Usuario> {
    private final Logger log = LoggerFactory.getLogger(UsuarioFacade.class);

    public UsuarioFacade() {
        super(Usuario.class);
    }

    /**
     * Convierte el password al tipo compatible con el JDBCRealm utilizado en el
     * login. -> Digest: SHA-512 -> Enconding: HEX -> Charset: UFT-8
     *
     * @param pwdNoCipher
     * @return Retorna el password encriptado con SHA512-HEX-UTF8
     * @throws Exception
     */
    public String setPasswordSHA512Hex(String pwdNoCipher) throws Exception {
        try {
            return AESSymetricCrypto.encriptInSHA512HEX2(pwdNoCipher);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Invoca a la sequencia de postgres que corresponde al valor por defecto
     * del campo usuario.idusuario, es por si no se utilice tipo SERIAL o si se
     * necesita utilizar el valor antes de su inserción.
     *
     * @return
     * @throws Exception
     */
    public int getSequence() throws Exception {
        try {
            String sql = "SELECT NEXTVAL('movil.usuario_idusuario_seq')";
            Query q = getEntityManager().createNativeQuery(sql);
            int newId = Integer.valueOf(q.getSingleResult().toString());

            log.info("Se obtuvo corretamente la sequencia de 'movil.usuario', new_id: {}", newId);

            return newId;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Usuario> findAllByActiveNative() throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT * ");
            sb.append(" FROM movil.usuario u  ");
            sb.append(" WHERE u.activo IS TRUE ");
            sb.append(" ORDER BY username ");

            Query q = getEntityManager().createNativeQuery(sb.toString(), Usuario.class);

            List l = q.getResultList();

            if (l == null || l.isEmpty()) {
                return null;
            } else {
                return l;
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    public Usuario findByUser(String username) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT u ");
            sb.append("  FROM Usuario u ");
            sb.append(" WHERE u.username = ?1");

            Query q = getEntityManager().createQuery(sb.toString());
            q.setParameter(1, username);
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");

            List l = q.getResultList();

            if (l.isEmpty()) {
                return null;
            } else {
                return (Usuario) l.get(0);
            }

        } catch (Exception ex) {
            throw new Exception("Error al recuperar usuario",ex);
        }
    }
    
    public List<Usuario> findByUserId(Integer idUser) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT u ");
            sb.append("  FROM Usuario u ");
            sb.append(" WHERE u.idusuario = ?1");

            Query q = getEntityManager().createQuery(sb.toString());
            q.setParameter(1, idUser);
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");

            List l = q.getResultList();

            if (l.isEmpty()) {
                return null;
            } else {
                return l;
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    public Usuario findByUserPass(Usuario user, String password) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT u ");
            sb.append("  FROM Usuario u ");
            sb.append(" WHERE u.idpersona = ?1 ");
            sb.append(" AND u.idusuario = ?2 ");
            sb.append(" AND u.password = ?3 ");

            Query q = getEntityManager().createQuery(sb.toString());
            q.setParameter(1, user.getPersona().getPersonaPK().getIdpersona());
            q.setParameter(2, user.getIdusuario());
            q.setParameter(3, setPasswordSHA512Hex(password));
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");

            List l = q.getResultList();

            if (l.isEmpty()) {
                return null;
            } else {
                return (Usuario) l.get(0);
            }

        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public List<Usuario> findByLogin(String login) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            login = "%" + login + "%";
            sb.append("SELECT u ");
            sb.append("  FROM Usuario u ");
            sb.append(" WHERE SQL('username ILIKE ?', ?1)");

            Query q = getEntityManager().createQuery(sb.toString());
            q.setParameter(1, login);

            List<Usuario> l = q.getResultList();

            if (l != null && !l.isEmpty()) {
                getEntityManager().detach(l.get(0));

                return l;
            }

            return null;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean validUser(String user) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("SELECT u.* ");
            sb.append("  FROM movil.usuario u ");
            sb.append(" WHERE u.username ILIKE TRIM(?1)");

            Query q = getEntityManager().createNativeQuery(sb.toString(), Usuario.class);
            q.setParameter(1, user);

            List<Usuario> l = q.getResultList();

            if (l.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<String> findUsuarioByDescription(String description) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT c.description ");
        sb.append("  FROM movil.usuario ");
        sb.append(" WHERE upper(c.username) LIKE upper(?1) ");
        sb.append(" ORDER BY c.description limit 6");

        Query q = getEntityManager().createNativeQuery(sb.toString());
        q.setParameter(1, description + "%");

        return q.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void insertUser(Usuario u) {
        try {
            create(u);
        } catch (Exception e) {
            log.info("Error al crear Usuario" + e);
        }
    }

    public void createUsuario(Integer idUsuario, Integer idPersona, String userName, String password, Boolean active, Integer idusuariodocumenta) throws Exception {
        try {
//            Usuario newUsuario = new Usuario();
//            newUsuario.setIdusuario(idUsuario);
//            newUsuario.setIdpersona(idPersona);
//            newUsuario.setUsername(userName);
//            newUsuario.setPassword(setPasswordSHA512Hex(password));
//            newUsuario.setActivo(active);
//            newUsuario.setIdusuariodocumenta(idusuariodocumenta);
//            create(newUsuario);
//
//            log.info(String.format("Creado el %s. Username: %s",
//                    newUsuario.getClass().getName(),
//                    newUsuario.getUsername()));
        } catch (Exception e) {
            throw e;
        }
    }

    public Integer getNumTryAccess(String username) throws Exception {
        Usuario user = findByUser(username);
        refresh(user);

        Integer intentosdeacceso = user.getIntentosdeacceso();

        return intentosdeacceso;
    }

    public void countTryAccess(String username) throws Exception {
        Integer numTryAccess = getNumTryAccess(username);
        numTryAccess++;
        setNumTryAccess(username, numTryAccess);
    }

    public void setNumTryAccess(String username, Integer num) throws Exception {
        Usuario user = findByUser(username);
        user.setIntentosdeacceso(num);
        edit(user);
        refresh(user);
    }

    public void setUsuarioTipoEstado(String username, ETipoEstadoUsuario tipoEstado) throws Exception {
        Usuario user = findByUser(username);
        user.setTipoestado(tipoEstado);

        if (tipoEstado.toString().length() >= 7) {
            // Se verifica todos los tipos de bloqueos.
            if (tipoEstado.name().contains("BLOCKED")) {
                //Date date = new Date();
                Calendar date = Calendar.getInstance();
                user.setFechabloqueoacceso(date.getTime());
                DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                log.info("Usuario bloqueado: {} fecha: {}", username, hourdateFormat.format(date.getTime()));
            }
        }

        if (tipoEstado.compareTo(ETipoEstadoUsuario.ACTIVE) == 0) {
            user.setIntentosdeacceso(0);
            user.setFechabloqueoacceso(null);
        }

        this.edit(user);
    }

    public List<Usuario> getUserForEmail(String email) throws Exception {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT c.*");
        sb.append("  FROM movil.usuario c ");
        sb.append(" WHERE c.correo = ?1 ");
        sb.append("   AND c.activo = true");

        Query q = getEntityManager().createNativeQuery(sb.toString(), Usuario.class);
        q.setParameter(1, email);
        List<Usuario> listaUsuario = q.getResultList();

        return listaUsuario;
    }

    public List<Usuario> getUserForNroCel(String nro) throws Exception {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT c.*");
        sb.append("  FROM movil.usuario c ");
        sb.append(" WHERE c.nrocel = ?1 ");
        sb.append("   AND c.activo = true");

        Query q = getEntityManager().createNativeQuery(sb.toString(), Usuario.class);
        q.setParameter(1, nro);
        List<Usuario> listaUsuario = q.getResultList();

        return listaUsuario;
    }

    public List<Usuario> findUserListWithoutPerson() throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT u.* ");
            sb.append("  FROM movil.usuario u ");
            sb.append("WHERE idpersona IS NULL ");
            sb.append("ORDER BY username");

            Query q = getEntityManager().createNativeQuery(sb.toString(), Usuario.class);
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List l = q.getResultList();

            if (l.isEmpty()) {
                return null;
            } else {
                return l;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Usuario> findUserListWithClient(Integer IdPersona) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT u.* ");
            sb.append("  FROM movil.usuario u ");
            sb.append(" WHERE idpersona = ?1 ");
            sb.append("ORDER BY username");

            Query q = getEntityManager().createNativeQuery(sb.toString(), Usuario.class);
            q.setParameter(1, IdPersona);
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List l = q.getResultList();

            if (l.isEmpty()) {
                return null;
            } else {
                return l;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Usuario findUser(Integer idPersona, String username) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT u.* ");
            sb.append("  FROM movil.usuario u ");
            sb.append(" WHERE u.idpersona = ?1 ");
            sb.append("   AND u.username = ?2");

            Query q = getEntityManager().createNativeQuery(sb.toString(), Usuario.class);
            q.setParameter(1, idPersona);
            q.setParameter(2, username);

            List l = q.getResultList();

            if (l.isEmpty()) {
                return null;
            } else {
                return (Usuario) l.get(0);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void updLastAccess(Usuario u) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE movil.usuario u SET lastaccess = CURRENT_TIMESTAMP ");
            sb.append(" WHERE u.idusuario = ?1");

            Query q = getEntityManager().createNativeQuery(sb.toString());

            q.setParameter(1, u.getIdusuario());

            if (q.executeUpdate() != 1) {
                throw new Exception("No se modificó el campo lastaccess. verifique el SQL.");
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
}
