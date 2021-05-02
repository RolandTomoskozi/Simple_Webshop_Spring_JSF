package at.rt.simple.webshop.core.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entity Mapping fuer Tabelle "bestellung".
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bestellung")
@Data
public class Bestellung extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "kunde_id")
    @ManyToOne
    private Kunde kunde;

    @NotNull
    private String bestellnummer;

    @Temporal(TemporalType.DATE)
    private Date datum;

    @NotBlank(message = "Bezeichnung darf nicht leer sein")
    private String bezeichnung;
}