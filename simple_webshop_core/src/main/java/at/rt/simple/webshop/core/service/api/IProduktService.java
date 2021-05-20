package at.rt.simple.webshop.core.service.api;

import at.rt.simple.webshop.core.model.domain.Produkt;
import at.rt.simple.webshop.core.model.dto.BestellungDto;

import java.util.List;

/**
 * Service-Interface fuer den Zugriff auf Produkten.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
public interface IProduktService {

    /**
     * Liefert die Liste alle Produkte.
     *
     * @return Liste alle Produkte
     */
    List<Produkt> listAlleProdukte();

    /**
     * Liefert die Produkte der ausgewaehlte Bestellug.
     *
     * @return Liste alle Produkte
     */
    List<Produkt> listProdukte(String bestellnummer);

    /**
     * Liefert die Produkte der ausgewaehlte Bestellug.
     *
     * @return Liste alle Produkte
     */
    List<Produkt> listProdukte(List<BestellungDto> bestellnummerList);

    /**
     * Speichert ein Produkt.
     *
     * @param produkt Zu speicherndem Produkt
     * @return gespeichertes Produkt
     */
    Produkt saveProdukt(Produkt produkt);

    /**
     * Loescht ein Produkt.
     *
     * @param produkt zu loeschender Produkt
     */
    void deleteProdukt(Produkt produkt);
}
