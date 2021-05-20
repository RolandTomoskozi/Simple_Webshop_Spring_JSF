package at.rt.simple.webshop.web.view.converter;

import at.rt.simple.webshop.core.model.domain.Kunde;
import at.rt.simple.webshop.core.service.api.IKundeService;
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
 * Test fuer {@link KundenConverter}.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@activesolution.at)
 * Created on 30.04.2021
 */
@ExtendWith(MockitoExtension.class)
public class KundenConverterTest {
    @InjectMocks
    private KundenConverter kundenConverter;

    @Mock
    private IKundeService kundeService;

    @Test
    @DisplayName("Converter Aufruf mit Null Value liefert Null")
    public void getAsObjectNullTest() {
        Object object = kundenConverter.getAsObject(null, null, null);
        Assertions.assertNull(object, "Null Value liefert null");
    }

    @Test
    @DisplayName("Converter Aufruf mit Empty Value liefert Null")
    public void getAsObjectEmptyTest() {
        Object object = kundenConverter.getAsObject(null, null, "");
        Assertions.assertNull(object, "Empty Value liefert null");
    }

    @Test
    @DisplayName("Converter Aufruf mit gueltiger ID liefert eine Kunde")
    public void getAsObjectValidIdTest() {
        // Mock erstellen
        Kunde kunde = createKunde();
        Mockito.doReturn(kunde).when(kundeService).getKundeById(ArgumentMatchers.eq(23L));

        Object object = kundenConverter.getAsObject(null, null, "23");
        Assertions.assertEquals(kunde, object);
    }

    private Kunde createKunde() {
        Kunde kunde = new Kunde();
        kunde.setId(23L);
        kunde.setName("Test Kunde");
        return kunde;
    }

    @Test
    @DisplayName("Converter Aufruf mit ungueltiger ID liefert null")
    public void getAsObjectInvalidIdTest() {
        // Mock erstellen
        Mockito.doReturn(null).when(kundeService).getKundeById(ArgumentMatchers.eq(42L));

        Object object = kundenConverter.getAsObject(null, null, "23");
        Assertions.assertNull(object);
    }

    @Test
    @DisplayName("Aufruf mit null-Object liefert Leerstring")
    public void getAsStringNullTest() {
        String string = kundenConverter.getAsString(null, null, null);
        Assertions.assertEquals("", string);
    }

    @Test
    @DisplayName("Aufruf mit Abteilung-Object liefert ID der Abteilung")
    public void getAsStringAbteilungTest() {
        // Mock erstellen
        Kunde kunde = createKunde();

        String string = kundenConverter.getAsString(null, null, kunde);
        Assertions.assertEquals("23", string);
    }
}
