package at.rt.simple.webshop.web.view.converter;

import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.service.api.IBestellungService;
import at.rt.simple.webshop.web.view.test.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Tests fuer {@link BestellungConverter}.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@activesolution.at)
 * Created on 30.04.2021
 */
@ExtendWith(MockitoExtension.class)
public class BestellungConverterTest {
    @InjectMocks
    BestellungConverter bestellungConverter;

    @Mock
    private IBestellungService bestellungService;

    @Test
    @DisplayName("Converter Aufruf mit Null Value liefert Null")
    public void getAsObjectNullTest() {
        Object object = bestellungConverter.getAsObject(null, null, null);
        Assertions.assertNull(object, "Null Value liefert null");
    }

    @Test
    @DisplayName("Converter Aufruf mit Empty Value liefert Null")
    public void getAsObjectEmptyTest() {
        Object object = bestellungConverter.getAsObject(null, null, "");
        Assertions.assertNull(object, "Empty Value liefert null");
    }

    @Test
    @DisplayName("Converter Aufruf mit gueltiger ID liefert eine Bestellung")
    public void getAsObjectValidIdTest() {
        // Mock erstellen
        Bestellung bestellung = createBestellung();
        Mockito.doReturn(bestellung).when(bestellungService).getBestellungById(ArgumentMatchers.eq(23L));

        Object object = bestellungConverter.getAsObject(null, null, "23");
        Assertions.assertEquals(bestellung, object);
    }

    private Bestellung createBestellung() {
        Bestellung bestellung = new Bestellung();
        bestellung.setId(23L);
        bestellung.setBezeichnung("Test Bestellung");
        return bestellung;
    }

    @Test
    @DisplayName("Converter Aufruf mit ungueltiger ID liefert null")
    public void getAsObjectInvalidIdTest() {
        // Mock erstellen
        Mockito.doReturn(null).when(bestellungService).getBestellungById(ArgumentMatchers.eq(42L));

        Object object = bestellungConverter.getAsObject(null, null, "23");
        Assertions.assertNull(object);
    }

    @Test
    @DisplayName("Aufruf mit null-Object liefert Leerstring")
    public void getAsStringNullTest() {
        String string = bestellungConverter.getAsString(null, null, null);
        Assertions.assertEquals("", string);
    }

    @Test
    @DisplayName("Aufruf mit Abteilung-Object liefert ID der Abteilung")
    public void getAsStringAbteilungTest() {
        // Mock erstellen
        Bestellung bestellung = createBestellung();

        String string = bestellungConverter.getAsString(null, null, bestellung);
        Assertions.assertEquals("23", string);
    }
}
