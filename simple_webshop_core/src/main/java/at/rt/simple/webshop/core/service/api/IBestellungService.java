package at.rt.simple.webshop.core.service.api;

import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.model.dto.BestellungDto;

import java.util.List;

/**
 * Service-Interface fuer den Zugriff auf Bestellung.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
public interface IBestellungService {

    /**
     * Liefert die List alle Bestellungen.
     *
     * @return Liste alle Bestellungen
     */
    List<Bestellung> listAlleBestellungen();

    /**
     * Liefert die Liste alle Bestellungen mit anzahl der Produkten.
     *
     * @param id Kunde id
     * @return Lust alle Bestellungen
     */
    List<BestellungDto> listBestellungDto(Long id);

    /**
     * Sucht eine Abteilung anhand der ID
     *
     * @param id ID der Bestellung
     * @return gefundene Bestellung
     */
    Bestellung getBestellungById(Long id);

    /**
     * Speichert eine Bestellung.
     *
     * @param bestellung Zu speichernde Bestellung
     * @return gespeicherte Bestellung
     */
    Bestellung saveBestellung(Bestellung bestellung);

    /**
     * Loescht eine Bestellung
     *
     * @param bestellung zu loeschender Bestellung
     */
    void deleteBestellung(Bestellung bestellung);
}
