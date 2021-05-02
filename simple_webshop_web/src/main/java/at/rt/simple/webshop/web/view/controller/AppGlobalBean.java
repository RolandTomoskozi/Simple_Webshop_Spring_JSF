package at.rt.simple.webshop.web.view.controller;

import org.hibernate.Session;
import org.primefaces.PrimeFaces;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Application-Bean haelt Informationen, die fuer alle Benutzer gleich sind. Wie z.B. Versionsinfos zur Anzeige.
 */
@Named
@Scope("application")
public class AppGlobalBean {

    private List<String> versionInfo;

    @PostConstruct
    public void init() {
        createVersionInfo();
    }

    /**
     * Erstellt die Liste an Versions-Infos zu Java/Server/Libraries
     */
    private void createVersionInfo() {
        List<String> result = new ArrayList<>();

        result.add("Java: " + System.getProperty("java.version") + " ("
                + System.getProperty("java.runtime.name") + " - "
                + System.getProperty("java.vendor") + ")");
        result.add("Server: " + ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getServerInfo());
        result.add(createVersionText("Spring", Service.class));
        result.add(createVersionText("Hibernate", Session.class));
        result.add(createVersionText("Servlet", ServletContext.class));
        result.add(createVersionText("JSF", FacesContext.class));
        result.add(createVersionText("PrimeFaces", PrimeFaces.class));

        this.versionInfo = result;
    }

    private String createVersionText(String lib, Class<?> clazz) {
        return lib + ": " + clazz.getPackage().getImplementationVersion();
    }

    public List<String> getVersionInfo() {
        return versionInfo;
    }

}
