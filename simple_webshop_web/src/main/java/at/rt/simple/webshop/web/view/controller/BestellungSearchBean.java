package at.rt.simple.webshop.web.view.controller;

import at.rt.simple.webshop.core.model.criteria.BestellungCriteria;
import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.service.api.IBestellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.List;

/**
 * Bean zur Suche nach Bestellungen.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@activesolution.at)
 * Created on 04.05.2021
 */
@Named
@Scope("view")
public class BestellungSearchBean extends BaseBackingBean {
    @Autowired
    private IBestellungService bestellungService;

    private BestellungCriteria bestellungCriteria = new BestellungCriteria();
    private List<Bestellung> bestellungList;

    public List<Bestellung> getBestellungList() {
        return bestellungList;
    }

    public BestellungCriteria getBestellungCriteria() {
        return bestellungCriteria;
    }

    public void actionSearch() {
        bestellungList = bestellungService.searchBestellung(bestellungCriteria);
    }

    public void actionCancel() {
        bestellungList = null;
        bestellungCriteria = new BestellungCriteria();
    }
}
