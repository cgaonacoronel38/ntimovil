package py.com.documenta.ntimovil.common;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * MessageUtil
 *
 * @author Oleg Varaksin / last modified by $Author: $
 * @version $Revision: 1.0 $
 */
public class MsgUtil {

    private static void addMessage(FacesMessage.Severity fm, String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fm, title, msg));
    }
    
    public static void addInfoMessage(String msg) {
        addMessage(FacesMessage.SEVERITY_INFO, msg, null);
    }
    
    public static void addWarningMessage(String msg) {
        addMessage(FacesMessage.SEVERITY_WARN, msg, null);
    }
    
    public static void addErrorMessage(String msg) {
        addMessage(FacesMessage.SEVERITY_ERROR, msg, null);
    }

    public static void addInfoMessage(String summary, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        String message = bundle.getString(summary);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail));
    }

    public static void addInfoMessage(String str, Object... args) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        String message = bundle.getString(str);
        if (args != null) {
            message = MessageFormat.format(message, args);
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }

    public static void addInfoMessageWithoutKey(String summary, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

    public static void addMessageWithoutKey(FacesMessage.Severity fms, String summary, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(fms, summary, detail));

    }
}
