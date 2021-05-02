package at.rt.simple.webshop.web.view;

import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LogConfigListener implements ServletContextListener {
    /**
     * Init the SLF4J Logging Bridge to route java util logging to slf4j
     *
     * @param sce ServletContext
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SLF4JBridgeHandler.install();
        SLF4JBridgeHandler.removeHandlersForRootLogger();
    }
}
