package at.rt.simple.webshop.core.service.api;

import at.rt.simple.webshop.core.model.criteria.BestellungCriteria;
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
     * Liefert die Liste alle Bestellungen.
     *
     * @return Liste alle Bestellungen
     */
    List<Bestellung> listAlleBestellungen();

    /**
     * Liefert die Liste alle Bestellungen mit anzahl der Produkten.
     *
     * @param id Kunde id
     * @return Liste alle Bestellungen
     */
    List<BestellungDto> listBestellungenDto(Long id);

    /**
     * Sucht eine Bestellung anhand der ID
     *
     * @param id ID der Bestellung
     * @return gefundene Bestellung
     */
    Bestellung getBestellungById(Long id);

    /**
     * Liefert die Liste der Betellung laut den uebergebenen Suchkriterien.
     *
     * @param bestellungCriteria Suchkriterien fuer Bestellungsuche
     * @return Liste von Bestellungen
     */
    List<Bestellung> searchBestellung(BestellungCriteria bestellungCriteria);

    /**
     * Speichert eine Bestellung.
     *
     * @param bestellung Zu speichernder Bestellung
     * @return gespeicherte Bestellung
     */
    Bestellung saveBestellung(Bestellung bestellung);

    /**
     * Loescht eine Bestellung.
     *
     * @param bestellung zu loeschender Bestellung
     */
    void deleteBestellung(Bestellung bestellung);
}
