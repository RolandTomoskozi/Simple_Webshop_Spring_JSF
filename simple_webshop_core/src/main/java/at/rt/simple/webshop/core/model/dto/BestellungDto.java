package at.rt.simple.webshop.core.model.dto;

import at.rt.simple.webshop.core.model.domain.Bestellung;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO kapselt die Bestellung und die Anzahl deren Produkten.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
@Data
@AllArgsConstructor
public class BestellungDto {
    private Bestellung bestellung;
    private Integer anzahlProdukt;
}
