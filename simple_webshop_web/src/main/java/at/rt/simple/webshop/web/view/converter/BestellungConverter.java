package at.rt.simple.webshop.web.view.converter;

import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.service.api.IBestellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 * Entity Converter fuer Bestellung. Liefert die ID als Wert.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 24.04.2021
 */
@Named
@Scope("application")
public class BestellungConverter implements Converter {
    @Autowired
    private IBestellungService bestellungService;

    /**
     * Aus JSF wird die ID als String uebergeben und hier ueber das Service in einen Entity umgewandelt.
     *
     * @param facesContext der FacesContext
     * @param uiComponent die Component
     * @param value eingegebener Wert
     * @return Bestellung-Entity oder <code>null</code>
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        Long id = Long.valueOf(value);

        return bestellungService.getBestellungById(id);
    }

    /**
     * Liefert die ID der Bestellung als String.
     *
     * @param facesContext der FacesContext
     * @param uiComponent die Component
     * @param object zu konvertierender Wert
     * @return ID der Abteilung
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if (object == null) {
            return "";
        }

        return ((Bestellung) object).getId().toString();
    }
}
