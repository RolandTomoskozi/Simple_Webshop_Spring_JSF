package at.rt.simple.webshop.core.model.criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * Suchkriterien fuer die Bestellung-Suche.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@activesolution.at)
 * Created on 04.05.2021
 */
public class BestellungCriteria implements Serializable {
    private String bezeichnung;
    private String bestellnummer;
    private Date datum;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBestellnummer() {
        return bestellnummer;
    }

    public void setBestellnummer(String bestellnummer) {
        this.bestellnummer = bestellnummer;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
