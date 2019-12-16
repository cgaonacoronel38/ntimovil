/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.filter;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.common.MsgUtil;
import py.com.documenta.ntimovil.controller.session.Config;
import py.com.documenta.ntimovil.controller.session.NavigateBean;
import py.com.documenta.ntimovil.controller.session.NtiSession;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.PermisoMenuFacade;

/**
 *
 * @author eduardo
 */
@WebFilter(filterName = "filterPermits", urlPatterns = {"/restricted/*"})
public class FilterPermits implements Filter {
    private static final Logger log = LoggerFactory.getLogger(FilterPermits.class);
    
    @Inject
    private PermisoMenuFacade permissionEJB;
    
    @Inject
    private NavigateBean nav;
    
    @Inject
    private NtiSession ntiMovilSession;
    
    @Inject
    private Config config;
    
    public FilterPermits() {
        
    }    

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Usuario su = null;
        boolean signIn = false;
        String suffix = null;
        try {
            suffix = config.appJSFSufix();
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);

            if(session == null || session.isNew()) {
                if(session != null) {
                    log.warn("La sessión es nueva, invalidando...");
                    
                    ntiMovilSession.removeUserInHttpSession();
                    ntiMovilSession.setUser(null);
                    
                    session.invalidate();
                    req.logout();
                }

                log.info("La sesión es nueva o expiró, debe loguearse nuevamente, redireccionando al home...");
                
                res.sendRedirect(res.encodeRedirectURL(nav.getHome()));
                
                return;
            }
            
            su = ntiMovilSession.getUser();
            signIn = ntiMovilSession.isSignIn(req);

            if (su == null || !signIn) {
                log.info("Usuario no logueado, no se podrá verificar los permisos, redireccionando al home.");
//                MsgUtil.addWarningMessage("Favor inicie cesión.");
                res.sendRedirect(nav.getIndex()); //vamos al home
                
                return;
            }
            
            String url = ((HttpServletRequest) request).getRequestURI();
            log.info("Url original: "+url);
            url = url.replace("." + suffix, "").trim().toLowerCase();
            
            if(!url.equalsIgnoreCase(ntiMovilSession.getPreviousURL())) {
                log.info("Parametrofr filro: "+url);
                Boolean urlValid = permissionEJB.isUrlValidNATIVE(url);
                
                if(urlValid == null) {
//                    MsgUtil.addWarningMessage("El recurso al que desea acceder no es válido o no se encuentra habilitado.");
                    log.warn("La url {} NO ES VALIDA, no se encuentra en la tabla de opciones.", url); 
                    res.sendRedirect(res.encodeRedirectURL(nav.getUserIndex() + "?status=4"));
                    
                    return;
                }
                
                if(!urlValid) {
                    log.warn("La url {} esta INHABILITADA.", url);
                    res.sendRedirect(res.encodeRedirectURL(nav.getUserIndex() + "?status=3"));
//                    MsgUtil.addWarningMessage("El recurso no se encuentra habilitado.");
                    return;
                }
                
                
                
                if (!permissionEJB.isPermissionNATIVE(su.getIdusuario(), url)) {
                    log.info("El usuario: {}, NO tiene permiso para la url: {}", su.getUsername(), url);
//                    MsgUtil.addWarningMessage("No posee privilegios para acceder a este recurso.");
                    res.sendRedirect(res.encodeRedirectURL(nav.getUserIndex() + "?status=1"));
                    
                    return;
                }
            }
            
            ntiMovilSession.setPreviousURL(url);
            
            log.info("El usuario: {} tiene permiso para la url: {}", su.getUsername(), url);
                
            chain.doFilter(request, response);
        } catch (Exception ex) {
            log.error("Error al verificar el permiso.", ex);
        }
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
