package at.rt.simple.webshop.web.view.controller;

import org.springframework.context.annotation.Scope;

import javax.inject.Named;

/**
 * Benutzer Konfiguration.
 */
@Named
@Scope("session")
public class UserBean {

    // der Default-Date-Pattern fuer den User
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    // die Default-Theme
    private String theme = "omega";

    /**
     * Liefert den Date-Pattern fuer die Verwendung im JSF Calendar.<br>
     * Koennte z.B. aus der FacesConfig je nach Locale ermittelt werden.
     *
     * @return DateFormat als String
     */
    public String getDatePattern() {
        return DATE_PATTERN;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }


}
