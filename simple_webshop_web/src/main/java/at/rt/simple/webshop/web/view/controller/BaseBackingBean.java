package at.rt.simple.webshop.web.view.controller;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Basisklasse fuer JSF BackingBeans.
 */
public class BaseBackingBean implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(BaseBackingBean.class);

    /**
     * Behandelt unerwartete Fehler
     *
     * @param e zu behandelnder Fehler
     */
    protected void handleException(Exception e) {
        LOG.error("Unerwarteter Fehler", e);

        // uebernimmt die info, dass ein fehler aufgetreten ist in die Ajax-Response
        PrimeFaces.current().ajax().addCallbackParam("unhandledError", true);

        // hier koennte man erweiterte Logik, je nach Exceptiontyp implementieren

        // gibt einfach den Fehlertext als Message aus
        FacesMessage facesMessage = new FacesMessage("Es ist ein unerwarteter Fehler aufgetreten: " + e.getMessage());
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
