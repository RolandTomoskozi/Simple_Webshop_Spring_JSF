package at.rt.simple.webshop.web.view.controller;

import at.rt.simple.webshop.core.service.api.IKundeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stellt die diversen SelectItems fuer Entities, etc. zur Verfuegung.
 */
@Named
@Scope("session")
public class LookupValuesBean {
    @Autowired
    private IKundeService kundeService;

    public List<SelectItem> getKunde() {
        return kundeService.listKunde()
                .stream().map(kunde -> new SelectItem(kunde, kunde.getName()))
                .collect(Collectors.toList());
    }
}