package at.rt.simple.webshop.core.service.impl;

import at.rt.simple.webshop.core.config.CoreConfig;
import at.rt.simple.webshop.core.model.domain.Produkt;
import at.rt.simple.webshop.core.service.api.IBestellungService;
import at.rt.simple.webshop.core.service.api.IProduktService;
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
public class ProduktenServiceTest {
    @Autowired
    private IProduktService produktService;
    @Autowired
    private IBestellungService bestellungService;

    @Test
    @DisplayName("Liste aller Produkten liefer X Eintraege in der richtigen Reihenfolge")
    public void listProduktenTest() {
        String bestellnummer1stBestellung = bestellungService.listAlleBestellungen().get(0).getBestellnummer();
        // Liste der Produkten
        List<Produkt> produktList = produktService.listProdukte(bestellnummer1stBestellung);

        // Anzahl pruefen
        Assertions.assertEquals(4, produktList.size());

        // ersten Produkt pruefen
        Produkt firstProdukt = produktList.get(0);
        Assertions.assertEquals(1L, firstProdukt.getId());
        Assertions.assertEquals("Tisch", firstProdukt.getBezeichnung());
        Assertions.assertEquals("IKEA", firstProdukt.getBestellung().getBezeichnung());
        Assertions.assertEquals("40f15dba-cf15-4fcb-8a70-9d5005d622c2", firstProdukt.getBestellnummer());
        Assertions.assertEquals("2892ee6c-542b-4192-860b-854c1e7fb372", firstProdukt.getArtikelnummer());
        Assertions.assertEquals(150, firstProdukt.getPreis());
        Assertions.assertEquals(1, firstProdukt.getPosition());
    }

    @Test
    @DisplayName("Speichern eines neuen Produkt testen")
    public void saveProduktTest() {
        Produkt produkt = new Produkt();
        produkt.setId(1L);
        produkt.setBestellnummer("669a166f-549d-4598-bf0f-54e62aa4eee5");
        produkt.setArtikelnummer("c4607007-425f-4ff2-98fb-48074233740b");
        produkt.setBezeichnung("Test Produkt");

        Produkt savedProdukt = produktService.saveProdukt(produkt);
        Assertions.assertNotNull(savedProdukt);

        Produkt firstProdukt = produktService.listAlleProdukte().get(0);
        Assertions.assertEquals(savedProdukt, firstProdukt);
    }

    @Test
    @DisplayName("Loeschen ein Produkt testen")
    public void deleteProduktTest() {
        List<Produkt> produktList = produktService.listAlleProdukte();
        Produkt firstProdukt = produktList.get(0);
        Produkt secondProdukt = produktList.get(1);

        // loeschen
        produktService.deleteProdukt(firstProdukt);

        List<Produkt> produktListAfterDelete = produktService.listAlleProdukte();
        Assertions.assertEquals(secondProdukt, produktListAfterDelete.get(0));
    }
}
