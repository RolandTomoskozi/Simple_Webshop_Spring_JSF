package at.rt.simple.webshop.core.service.api;

import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.model.dto.BestellungInfoDto;

import java.util.List;

/**
 * Service-Interface fuer den Zugriff auf Bestellung.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
public interface IBestellungService {
    /**
     * Liefert die Liste aller Bestellungen, sortiert nach der Bezeichnung.
     *
     * @return Liste von Bestellungen
     */
    List<Bestellung> listBestellung();

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

    /**
     * Liefert die Anzahl der Produkten je Bestellung
     * @return DTO mitBestellungsInfos
     */
    List<BestellungInfoDto> getBestellungInfo();
}
