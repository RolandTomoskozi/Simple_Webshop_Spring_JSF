package at.rt.simple.webshop.core.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Entity Mapping fuer Tabelle "produkt".
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "produkt")
@Data
public class Produkt extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "bestellung_id")
    @ManyToOne
    private Bestellung bestellung;

    @NotNull
    private String bestellnummer;

    @NotBlank
    private String artikelnummer;

    private int position;

    @NotBlank
    private String bezeichnung;

    private int preis;
}