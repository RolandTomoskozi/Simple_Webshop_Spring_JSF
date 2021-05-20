package at.rt.simple.webshop.core.service.impl;

import at.rt.simple.webshop.core.config.CoreConfig;
import at.rt.simple.webshop.core.model.domain.Kunde;
import at.rt.simple.webshop.core.model.dto.KundeDto;
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
public class KundenServiceTest {
    @Autowired
    private IKundeService kundeService;

    @Test
    @DisplayName("Aufruf listKunden liefert richtige Daten und Sortierung")
    public void listKundenTest() {
        // Service liefert die Testdaten lt. test-data-kunde.sql
        List<Kunde> kunden = kundeService.listKunde();

        // 7 Kunden gefunden
        Assertions.assertEquals("Active Solution", kunden.get(0).getName());
        Assertions.assertEquals("BRZ", kunden.get(1).getName());
        Assertions.assertEquals("Drei", kunden.get(2).getName());
        Assertions.assertEquals("A1", kunden.get(3).getName());
        Assertions.assertEquals("Magenta", kunden.get(4).getName());
        Assertions.assertEquals("Beko", kunden.get(5).getName());
        Assertions.assertEquals("Allianz", kunden.get(6).getName());
    }

    @Test
    @DisplayName("Kunden anhand der ID lesen")
    public void getKundenByIdTest() {
        Kunde kunde = kundeService.getKundeById(1L);
        Assertions.assertNotNull(kunde);
        Assertions.assertEquals("Active Solution", kunde.getName());
        Assertions.assertEquals("6401be66-1891-4e34-a40f-38e7f6069f91", kunde.getKundennummer());
    }

    @Test
    public void getKundeDtoTest() {
        List<KundeDto> kundeDtos = kundeService.listKundeDto();

        Assertions.assertEquals(7, kundeDtos.size());
        Assertions.assertEquals("Active Solution", kundeDtos.get(0).getKunde().getName());
        Assertions.assertEquals("BRZ", kundeDtos.get(1).getKunde().getName());
        Assertions.assertEquals("Drei", kundeDtos.get(2).getKunde().getName());
        Assertions.assertEquals("A1", kundeDtos.get(3).getKunde().getName());
        Assertions.assertEquals("Magenta", kundeDtos.get(4).getKunde().getName());
        Assertions.assertEquals("Beko", kundeDtos.get(5).getKunde().getName());
        Assertions.assertEquals("Allianz", kundeDtos.get(6).getKunde().getName());

        Assertions.assertEquals(Long.valueOf(2), kundeDtos.get(0).getAnzahlVonBestellung());
        Assertions.assertEquals(Long.valueOf(1), kundeDtos.get(1).getAnzahlVonBestellung());
        Assertions.assertEquals(Long.valueOf(3), kundeDtos.get(2).getAnzahlVonBestellung());
        Assertions.assertEquals(Long.valueOf(1), kundeDtos.get(3).getAnzahlVonBestellung());
    }

    @Test
    public void saveKundeTest() {
        Kunde kunde = new Kunde();
        kunde.setName("Test Kunde");
        kunde.setKundennummer("4c639daf-cdee-4298-8f39-05c125acd90b");

        Kunde saved = kundeService.saveKunde(kunde);

        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(kunde, saved);

        Long id = saved.getId();

        Kunde loaded = kundeService.getKundeById(id);

        Assertions.assertNotNull(loaded);
        Assertions.assertEquals("Test Kunde", kunde.getName());
        Assertions.assertEquals("4c639daf-cdee-4298-8f39-05c125acd90b", kunde.getKundennummer());
    }

    @Test
    @DisplayName("Loeschen eine Kunde testen")
    public void deleteKundeTest() {
        List<Kunde> kundeList = kundeService.listKunde();
        Kunde firtKunde = kundeList.get(0);
        Kunde secondKunde = kundeList.get(1);

        // loeschen
        kundeService.deleteKunde(firtKunde);

        List<Kunde> kundeListAfterDelete = kundeService.listKunde();
        Assertions.assertEquals(secondKunde, kundeListAfterDelete.get(0));
    }
}
