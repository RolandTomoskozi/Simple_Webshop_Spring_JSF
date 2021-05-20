package at.rt.simple.webshop.web.view.converter;

import at.rt.simple.webshop.core.model.domain.Kunde;
import at.rt.simple.webshop.core.service.api.IKundeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 * Entity Converter fuer Kunde. Liefert die ID als Wert.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 24.04.2021
 */
@Named
@Scope("application")
public class KundenConverter implements Converter {
    @Autowired
    private IKundeService kundeService;

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

        return kundeService.getKundeById(id);
    }

    /**
     * Liefert die ID der Kunde als String.
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

        return ((Kunde) object).getId().toString();
    }
}
