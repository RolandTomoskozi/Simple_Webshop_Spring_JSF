package at.rt.simple.webshop.core.service.impl;

import at.rt.simple.webshop.core.config.CoreConfig;
import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.model.dto.BestellungDto;
import at.rt.simple.webshop.core.service.api.IBestellungService;
import at.rt.simple.webshop.core.service.api.IKundeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Test mit Spring-Support.<br>
 * Test laeuft in einer Transaktion, fuer die automatisch nach den einzelnen Tests
 * ein Rollback ausgeloest wird.
 * So beeinflussen sich die Daten der einzelnen Tests nicht gegenseitig.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@activesolution.at)
 * Created on 30.04.2021
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CoreConfig.class)
@Transactional
public class BestellungServiceTest {
    @Autowired
    private IBestellungService bestellungService;
    @Autowired
    private IKundeService kundeService;

    @Test
    @DisplayName("Liste aller Bestellungen liefer 5 Eintraege in der richtigen Reihenfolge")
    public void listBestellungTest() {
        // Liste der Bestellungen
        List<Bestellung> bestellungList = bestellungService.listAlleBestellungen();

        // Anzahl pruefen
        Assertions.assertEquals(7, bestellungList.size());

        // ersten Bestellungen pruefen
        Bestellung firstBestellung = bestellungList.get(0);
        Assertions.assertEquals("IKEA", firstBestellung.getBezeichnung());
        Assertions.assertEquals("2020-11-11", firstBestellung.getDatum().toString());
        Assertions.assertEquals("40f15dba-cf15-4fcb-8a70-9d5005d622c2", firstBestellung.getBestellnummer());
        Assertions.assertEquals("Active Solution", firstBestellung.getKunde().getName());
    }

    @Test
    @DisplayName("Speichern eines neuen Bestellung testen")
    public void saveBestellungTest() {
        Bestellung bestellung = new Bestellung();
        bestellung.setId(1L);
        bestellung.setBezeichnung("Test Bestellung");
        bestellung.setBestellnummer("32202443-af5a-4d44-8cd1-192724b01ad6");

        Bestellung savedBestellung = bestellungService.saveBestellung(bestellung);
        Assertions.assertNotNull(savedBestellung);

        Bestellung firstBestellng = bestellungService.listAlleBestellungen().get(0);
        Assertions.assertEquals(savedBestellung, firstBestellng);
    }

    @Test
    @DisplayName("Loeschen eine Bestellung testen")
    public void deleteBestellungTest() {
        List<Bestellung> bestellungList = bestellungService.listAlleBestellungen();
        Bestellung firstBestellung = bestellungList.get(0);
        Bestellung secondBestellung = bestellungList.get(1);

        // loeschen
        bestellungService.deleteBestellung(firstBestellung);

        List<Bestellung> bestellungListAfterDelete = bestellungService.listAlleBestellungen();
        Assertions.assertEquals(secondBestellung, bestellungListAfterDelete.get(0));
    }

    @Test
    public void getbestellungDtoTest() {
        Long idFirstKunde = kundeService.listKunde().get(0).getId();
        List<BestellungDto> bestellungDto = bestellungService.listBestellungenDto(idFirstKunde);

        // Check Bestellungen
        Assertions.assertEquals(2, bestellungDto.size());
        Assertions.assertEquals("IKEA", bestellungDto.get(0).getBestellung().getBezeichnung());
        Assertions.assertEquals("Libro", bestellungDto.get(1).getBestellung().getBezeichnung());

        // Check die Produktenanzahl der Bestellungen
        Assertions.assertEquals(Long.valueOf(4), bestellungDto.get(0).getAnzahlVonProdukten());
        Assertions.assertEquals(Long.valueOf(4), bestellungDto.get(1).getAnzahlVonProdukten());
    }
}
