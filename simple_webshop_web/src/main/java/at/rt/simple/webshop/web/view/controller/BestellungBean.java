package at.rt.simple.webshop.web.view.controller;

import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.model.domain.Kunde;
import at.rt.simple.webshop.core.model.domain.Produkt;
import at.rt.simple.webshop.core.model.dto.BestellungDto;
import at.rt.simple.webshop.core.model.dto.KundeDto;
import at.rt.simple.webshop.core.service.api.IBestellungService;
import at.rt.simple.webshop.core.service.api.IKundeService;
import at.rt.simple.webshop.core.service.api.IProduktService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Bean zur Anzeige der Kundenliste, Bestellunglist, Produktliste und zum alle sie bearbeiten.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 24.04.2021
 */
@Named
public class BestellungBean extends BaseBackingBean {
    @Autowired
    private IKundeService kundeService;
    @Autowired
    private IBestellungService bestellungService;
    @Autowired
    private IProduktService produktService;

    private Bestellung currentBestellung;
    private Kunde currentKunde;
    private Produkt currentProdukt;

    private List<KundeDto> kundenList;
    private List<BestellungDto> bestellungList;
    private List<Produkt> produktList;

    private String header;

    @PostConstruct
    private void onInit() {
        refreshKunde();
    }

    // GET
    public List<KundeDto> getKundenList() {
        kundenList = kundeService.listKundeDto();

        if (kundenList == null) {
            return Collections.emptyList();
        }

        return kundenList;
    }

    public List<BestellungDto> getBestellungList() {
        if (bestellungList == null) {
            return Collections.emptyList();
        }

        return bestellungList;
    }

    public List<Produkt> getProduktList() {
        if (produktList == null) {
            return Collections.emptyList();
        }

        return produktList;
    }

    // Edit

    /**
     * Setzt eine Kunden zur Bearbeitung.
     */
    public void actionEditKunde(Kunde kunde) {
        header = "Kunde bearbeiten";
        this.currentKunde = kunde;
    }

    /**
     * Erstellt ein neue Bestellung Objekt zur Anlage.
     */
    public void actionEditBestellung(Bestellung bestellung) {
        header = "Neues Bestellung";
        this.currentBestellung = bestellung;
    }

    /**
     * Setzt eine Produkt zu Bearbeitung.
     */
    public void actionEditProdukt(Produkt produkt) {
        header = "Produkt bearbeiten";
        this.currentProdukt = produkt;
    }

    // New

    /**
     * Erstellt ein neue Kunde Objekt zur Anlage.
     */
    public void actionNewKunde() {
        setHeader("Neue Kunde");
        currentKunde = new Kunde();

        // erstellt ein random UUID
        String uuid = UUID.randomUUID().toString();

        // sammelt die Kundennummern von Kunden und speichert sie in einem List
        List<String> uuidList = kundenList
                .stream()
                .map(kundeDto -> kundeDto.getKunde().getKundennummer()).collect(Collectors.toList());

        // loopt durch die Liste von Kundennummern und überprüft, ob sie noch nicht vorhanden ist
        while (uuidList.contains(uuid)) {
            // generiert solange eine neue UUID, bis sie uniq wird
            uuid = UUID.randomUUID().toString();
        }

        currentKunde.setKundennummer(uuid);
    }

    /**
     * Setzt eine Bestellung zur Bearbeitung.
     */
    public void actionNewBestellung() {
        setHeader("Neues Bestellung");
        currentBestellung = new Bestellung();

        // erstellt ein random UUID
        String uuid = UUID.randomUUID().toString();

        // sammelt die Bestelnummern von Bestellungen und speichert sie in einem List
        List<String> uuidList = bestellungList
                .stream()
                .map(bestellungDto -> bestellungDto.getBestellung().getBestellnummer()).collect(Collectors.toList());

        // loopt durch die Liste von Kundennummern und überprüft, ob sie noch nicht vorhanden ist
        while (uuidList.contains(uuid)) {
            // generiert solange eine neue UUID, bis sie uniq wird
            uuid = UUID.randomUUID().toString();
        }

        currentBestellung.setBestellnummer(uuid);
    }

    /**
     * Erstellt ein neue Produkt Objekt zu Anlage.
     */
    public void actionNewProdukt() {
        setHeader("Neus Produkt");
        currentProdukt = new Produkt();

        // erstellt ein random UUID
        String uuid = UUID.randomUUID().toString();

        // sammelt die Artikelnummern von Produkten und speichert sie in einem List
        List<String> uuidList = produktList
                .stream()
                .map(Produkt::getArtikelnummer).collect(Collectors.toList());

        // loopt durch die Liste von Kundennummern und überprüft, ob sie noch nicht vorhanden ist
        while (uuidList.contains(uuid)) {
            // generiert solange eine neue UUID, bis sie uniq wird
            uuid = UUID.randomUUID().toString();
        }

        currentProdukt.setArtikelnummer(uuid);
    }

    // Delete

    /**
     * Loescht den uebergebene Kunde aus der DB.
     *
     * @param kunde zu loeschender Kunde
     */
    public void actionDeleteKunde(Kunde kunde) {
        try {
            kundeService.deleteKunde(kunde);
            refreshKunde();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kunde wurde gelöscht!"));
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Loescht den uebergebene Bestellung aus der DB.
     */
    public void actionDeleteBestellung(Bestellung bestellung) {
        try {
            bestellungService.deleteBestellung(bestellung);
            refreshBestellung();
            refreshKunde();

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Bestellung wurde gelöscht!"));

        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Loescht den uebergebene Produkt aus der DB.
     */
    public void actionDeleteProdukt(Produkt produkt) {
        try {
            produktService.deleteProdukt(produkt);

            // refresht die Kunden und damit gliechzeitig die Bestellungen
            refreshKunde();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produkt wurde gelöscht!"));
        } catch (Exception e) {
            handleException(e);
        }
    }

    // Save

    /**
     * Speichert den aktuell bearbeiteten Kunde.
     */
    public void actionSaveKunde() {
        try {
            kundeService.saveKunde(currentKunde);

            // reload Kunden
            refreshKunde(currentKunde);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Daten wurden gespeichert!"));
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Speichert den aktuell bearbeiteten Bestellung.
     */
    public void actionSaveBestellung() {
        try {
            bestellungService.saveBestellung(currentBestellung);

            // reload (wegen Anzahl von Bestellungen)
            refreshKunde();

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Daten wurden gespeschert!"));

        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Speichert den aktuell bearbeiteten Produkt.
     */
    public void actionSaveProdukt() {
        try {
            // set die selbe bestellnummer, wie seiner Bestellung
            if (currentProdukt.getBestellung() != null) {
                currentProdukt.setBestellnummer(currentProdukt.getBestellung().getBestellnummer());
            }

            // speichert das Produkt
            produktService.saveProdukt(currentProdukt);

            if (!produktList.contains(currentProdukt)) {
                produktList.add(currentProdukt);
            }

            // reload (wegen Anzahl von Produkten)
            refreshKunde();

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Daten wurden gespeischert!"));

        } catch (Exception e) {
            handleException(e);
        }
    }

    // Cancel
    public void actionCancelKunde() {
        currentKunde = null;
    }

    public void actionCancelBestellung() {
        currentBestellung = null;
    }

    public void actionCancelProdukt() {
        currentProdukt = null;
    }

    // Reset

    /**
     * Kundenliste und aktuelle Kunde zuruecksetzen.
     */
    private void resetKunde() {
        currentKunde = null;
        kundenList = null;
        setBestellungList(Collections.emptyList());
    }

    /**
     * Bestellungliste und aktuelle Bestellung zuruecksetzen.
     */
    private void resetBestellung() {
        currentBestellung = null;
        bestellungList = null;
        setProduktList(Collections.emptyList());
    }

    // Refresh
    private void refreshKunde(Kunde kunde) {
        kundenList = kundeService.listKundeDto();

        // Wenn die Kundenlist nicht leer ist, holt mir (durch den BestellungBean) die esrte Elemente
        if (!kundenList.isEmpty()) {
            onBestellungListWithKundeId(kunde.getId());
        } else {
            resetKunde();
        }
    }

    private void refreshKunde() {
        kundenList = kundeService.listKundeDto();

        if (!kundenList.isEmpty()) {
            onBestellungListWithKundeId(kundenList.get(0).getKunde().getId());
        } else {
            resetKunde();
        }
    }

    private void refreshBestellung() {
        if (!bestellungList.isEmpty()) {
            onProduktListWithBestellId(bestellungList.get(0).getBestellung().getBestellnummer());
        } else {
            resetBestellung();
        }
    }

    // ---------------------------------------------------- Mapper -----------------------------------------------------

    public void onBestellungListWithKundeId(Long id) {
        bestellungList = bestellungService.listBestellungDto(id);

        if (!bestellungList.isEmpty()) {
            onProduktListWithBestellId(bestellungList);
        } else {
            setProduktList(Collections.emptyList());
        }
    }

    public List<SelectItem> getBestellungen() {
        return bestellungList
                .stream().map(b -> new SelectItem(b.getBestellung(), b.getBestellung().getBezeichnung()))
                .collect(Collectors.toList());
    }

    public void onProduktListWithBestellId(String bestellnummer) {
        produktList = produktService.listProdukt(bestellnummer);
    }

    public void onProduktListWithBestellId(List<BestellungDto> bestellungNummerList) {
        produktList = produktService.listProdukt(bestellungNummerList);
    }

    // ------------------------------------------------ Getter & Setter ------------------------------------------------

    // Header
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    // Kunde
    public void setCurrentKunde(Kunde currentKunde) {
        this.currentKunde = currentKunde;
    }

    public Kunde getCurrentKunde() {
        return currentKunde;
    }

    // Bestellung

    public Bestellung getCurrentBestellung() {
        return currentBestellung;
    }

    public void setCurrentBestellung(Bestellung currentBestellung) {
        this.currentBestellung = currentBestellung;
    }

    public void setBestellungList(List<BestellungDto> bestellungList) {
        this.bestellungList = bestellungList;
    }

    // Produkt

    public Produkt getCurrentProdukt() {
        return currentProdukt;
    }

    public void setCurrentProdukt(Produkt currentProdukt) {
        this.currentProdukt = currentProdukt;
    }

    public void setProduktList(List<Produkt> produktList) {
        this.produktList = produktList;
    }
}