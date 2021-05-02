package at.rt.simple.webshop.core.service.api;

import at.rt.simple.webshop.core.model.domain.Kunde;
import at.rt.simple.webshop.core.model.dto.KundeDto;

import java.util.List;

/**
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 24.04.2021
 */
public interface IKundeService {

    /**
     * Liefert die Liste alle Kunden.
     *
     * @return Liste alle Kunden
     */
    List<Kunde> listKunde();

    /**
     * Liefert die Liste alle Kunden mit Anzahl der Bestellungen.
     *
     * @return Liste alle Kunden
     */
    List<KundeDto> listKundeDto();

    /**
     * Sucht eine Kunde anhand der ID
     *
     * @param id ID der Kunde
     * @return gefundene Kunde
     */
    Kunde getKundeById(Long id);

    /**
     * Speichert eine Kunde.
     *
     * @param kunde Zu speichernder Kunde
     * @return gepsciherte Kunde
     */
    Kunde saveKunde(Kunde kunde);

    /**
     * Loescht eine Kunde.
     *
     * @param kunde zu loeschender Kunde
     */
    void deleteKunde(Kunde kunde);
}
