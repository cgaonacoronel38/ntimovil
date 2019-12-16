package py.com.documenta.ntimovil.ejb.bean.setup;

import javax.ejb.Stateless;
import py.com.documenta.ntimovil.ejb.manager.ResourceManager;

/**
 *
 * @author tokio
 */
@Stateless
public class SetupManager {

    public ResourceManager instance() throws Exception {
        return ResourceManager.getInstance();
    }

}