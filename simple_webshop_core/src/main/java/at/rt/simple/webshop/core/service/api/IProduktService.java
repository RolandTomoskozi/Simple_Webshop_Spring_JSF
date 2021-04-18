package at.rt.simple.webshop.core.service.api;

import at.rt.simple.webshop.core.model.domain.Produkt;

import java.util.List;

/**
 * Service-Interface fuer den Zugriff auf Produkten.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
public interface IProduktService {
    /**
     * Liefert die Liste aller Produkten, sortiert nach der Bezeichnung.
     *
     * @return Liste von Produkten
     */
    List<Produkt> listProdukt(int bestellnummer);

    /**
     * Speichert ein Produkt.
     *
     * @param produkt Zu speichernden Produkt
     * @return gespeicherte Produkt
     */
    Produkt saveProdukt(Produkt produkt);

    /**
     * Loescht ein Produkt
     *
     * @param produkt zu loeschender Produkt
     */
    void deleteProdukt(Produkt produkt);
}
