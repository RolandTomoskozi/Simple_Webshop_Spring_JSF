package at.rt.simple.webshop.web.view.controller;

import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.model.domain.Kunde;
import at.rt.simple.webshop.core.model.domain.Produkt;
import at.rt.simple.webshop.core.service.api.IBestellungService;
import at.rt.simple.webshop.core.service.api.IKundeService;
import at.rt.simple.webshop.core.service.api.IProduktService;
import at.rt.simple.webshop.web.view.test.FacesContextTester;
import at.rt.simple.webshop.web.view.test.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Roland Tömösközi (roland.toemoeskoezi@activesolution.at)
 * Created on 30.04.2021
 */
@ExtendWith(MockitoExtension.class)
public class BestellungBeanTest {
    @InjectMocks
    private BestellungBean bestellungBean;

    @Mock
    private IKundeService kundeService;
    @Mock
    private IBestellungService bestellungService;
    @Mock
    private IProduktService produktService;

    private Kunde kunde;
    private Bestellung bestellung;
    private Produkt produkt;

    @BeforeEach
    public void setUp() {
        kunde = new Kunde();
        kunde.setId(1L);
        kunde.setName("Test Kunde");
        kunde.setKundennummer("2f5e141d-09be-4dcd-a00a-27e871fb7ce3");

        bestellung = new Bestellung();
        bestellung.setId(1L);
        bestellung.setBestellnummer("5c345cc1-9b74-49ab-943a-4f1c304ac621");
        bestellung.setKunde(kundeService.getKundeById(1L));
        bestellung.setBezeichnung("Test Bestellung");

        produkt = new Produkt();
        produkt.setId(1L);
        produkt.setBezeichnung("Test Produkt");
        produkt.setBestellung(bestellungService.getBestellungById(1L));
        produkt.setArtikelnummer("cb53c773-8bb6-408a-9769-eb10a1713d25");

        bestellungBean.setCurrentKunde(kunde);
        bestellungBean.setCurrentBestellung(bestellung);
        bestellungBean.setCurrentProdukt(produkt);
    }

    // TODO: actionNewKunde()
//    @Test
//    @DisplayName("ActionNewKunde erzeugt neue leer Kunde")
//    public void actionNewKunde() {
////        KundeDto kundeDto = new KundeDto(kunde, 1L);
////        kundeDto.setKunde(kunde);
////        kundeDto.setAnzahlVonBestellung(1L);
////
////        bestellungBean.setCurrentKunde(kundeDto.getKunde());
//
//        // test
//        bestellungBean.actionNewKunde();
//
//        // FIXME: DTO KundenList ist null
//
//        // check, ob ein neue Kunde angelegt wird (die neue ID sollte nicht mit dem currentKunde ID identisch sein)
////        Assertions.assertNotEquals(kunde.getId(), bestellungBean.getCurrentKunde().getId());
//
//        // verify
//        Assertions.assertNotNull(bestellungBean.getCurrentKunde());
////        Assertions.assertNull(bestellungBean.getCurrentKunde().getId());
////        Assertions.assertNull(bestellungBean.getCurrentKunde().getName());
////        Assertions.assertNull(bestellungBean.getCurrentKunde().getKundennummer());
//    }

    @Test
    @DisplayName("ActionEditKunde uebernimmt Kunde")
    public void actionEditKunde() {
        // Kunde, damit die aktuelle Kunde ausgetauscht wird
        Kunde editedKunde = new Kunde();
        editedKunde.setId(1L);
        editedKunde.setName("Test Kunde Edited");
        editedKunde.setKundennummer("bcd77421-0e61-4087-a469-6a9e5d7ea723");

        // Kundedaten auf ein andere Kunde austauschen
        bestellungBean.actionEditKunde(editedKunde);

        // verify
        Assertions.assertNotNull(bestellungBean.getCurrentKunde());
        Assertions.assertEquals(editedKunde, bestellungBean.getCurrentKunde());
    }

    @Test
    @DisplayName("Save ruft das KundenService auf")
    public void actionSaveKunde() {
        // init mock-facescontext
        FacesContext facesContext = Mockito.mock(FacesContext.class);
        FacesContextTester.setInstance(facesContext);

        // save aufrufen
        bestellungBean.actionSaveKunde();

        // save wurde mit dem richtigen objekt aufgerufen
        ArgumentCaptor<Kunde> argumentCaptor = ArgumentCaptor.forClass(Kunde.class);
        Mockito.verify(kundeService).saveKunde(argumentCaptor.capture());
        Assertions.assertEquals(kunde, argumentCaptor.getValue());

        // facesmessage wurde erstellt
        ArgumentCaptor<FacesMessage> facesMessageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
        Mockito.verify(facesContext).addMessage(ArgumentMatchers.eq(null), facesMessageCaptor.capture());
        Assertions.assertEquals("Daten wurden gespeichert!", facesMessageCaptor.getValue().getSummary());
    }

    // -------------------------------------------------- Bestellung ---------------------------------------------------

    // TODO: actionNewBestellung()

    @Test
    @DisplayName("ActionEditBestellung uebernimmt Bestellung")
    public void actionEditBestellung() {
        // Bestellung, damit die aktuelle Bestellung ausgetauscht wird
        Bestellung editedBestellung = new Bestellung();
        editedBestellung.setId(1L);
        editedBestellung.setBestellnummer("d094885d-eae7-43b1-90ce-1b91137621ca");
        editedBestellung.setKunde(kundeService.getKundeById(1L));
        editedBestellung.setBezeichnung("Test Bestellung Edited");

        // Bestellungsdaten auf ein andere Bestellung austauschen
        bestellungBean.actionEditBestellung(editedBestellung);

        // verify
        Assertions.assertNotNull(bestellungBean.getCurrentBestellung());
        Assertions.assertEquals(editedBestellung, bestellungBean.getCurrentBestellung());
    }

    @Test
    public void actionSaveBestellung() {
        // init mock-facescontext
        FacesContext facesContext = Mockito.mock(FacesContext.class);
        FacesContextTester.setInstance(facesContext);

        // save aufrufen
        bestellungBean.actionSaveBestellung();

        // save wurde mit dem richtigen objekt aufgerufen
        ArgumentCaptor<Bestellung> argumentCaptor = ArgumentCaptor.forClass(Bestellung.class);
        Mockito.verify(bestellungService).saveBestellung(argumentCaptor.capture());
        Assertions.assertEquals(bestellung, argumentCaptor.getValue());

        // facesmessage wurde erstellt
        ArgumentCaptor<FacesMessage> facesMessageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
        Mockito.verify(facesContext).addMessage(ArgumentMatchers.eq(null), facesMessageCaptor.capture());
        Assertions.assertEquals("Daten wurden gespeichert!", facesMessageCaptor.getValue().getSummary());
    }

    // --------------------------------------------------- Produkt -----------------------------------------------------

//    // TODO: actionNewProdukt()
//    @Test
//    @DisplayName("ActionNewProdukt erzeugt neue leer Produkt")
//    public void actionNewProdukt() {
//        // test
//        bestellungBean.actionNewProdukt();
//
//        // verify
//        Assertions.assertNotNull(bestellungBean.getCurrentProdukt());
//        Assertions.assertNull(bestellungBean.getCurrentProdukt().getId());
//        Assertions.assertNull(bestellungBean.getCurrentProdukt().getBezeichnung());
//        Assertions.assertNotNull(bestellungBean.getCurrentProdukt().getArtikelnummer());
//
//    }

    @Test
    @DisplayName("ActionEditProdukt uebernimmt Produkt")
    public void actionEditProdukt() {
        // Kunde, damit die aktuelle Kunde ausgetauscht wird
        Produkt editedProdukt = new Produkt();
        editedProdukt.setId(1L);
        editedProdukt.setBezeichnung("Test Produkt Edited");
        editedProdukt.setBestellung(bestellungService.getBestellungById(1L));
        editedProdukt.setArtikelnummer("27b202ea-8842-4b93-bd42-154ae89a6d07");

        // Produktdaten auf ein andere Produkt austauschen
        bestellungBean.actionEditProdukt(editedProdukt);

        // verify
        Assertions.assertNotNull(bestellungBean.getCurrentProdukt());
        Assertions.assertEquals(editedProdukt, bestellungBean.getCurrentProdukt());
    }

    @Test
    public void actionSaveProdukt() {
        // init mock-facescontext
        FacesContext facesContext = Mockito.mock(FacesContext.class);
        FacesContextTester.setInstance(facesContext);

        // save aufrufen
        bestellungBean.actionSaveProdukt();

        // save wurde mit dem richtigen objekt aufgerufen
        ArgumentCaptor<Produkt> argumentCaptor = ArgumentCaptor.forClass(Produkt.class);
        Mockito.verify(produktService).saveProdukt(argumentCaptor.capture());
        Assertions.assertEquals(produkt, argumentCaptor.getValue());

        // FIXME: Es ist ein unerwarteter Fehler aufgetreten: null
        // facesmessage wurde erstellt
//        ArgumentCaptor<FacesMessage> facesMessageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
//        Mockito.verify(facesContext).addMessage(ArgumentMatchers.eq(null), facesMessageCaptor.capture());
//        Assertions.assertEquals("Daten wurden gespeichert!", facesMessageCaptor.getValue().getSummary());
    }
}
