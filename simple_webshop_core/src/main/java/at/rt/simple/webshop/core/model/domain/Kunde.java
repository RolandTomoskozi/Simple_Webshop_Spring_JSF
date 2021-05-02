package at.rt.simple.webshop.core.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Entity Mapping fuer Tabelle "kunde".
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 24.04.2021
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "kunde")
@Data
public class Kunde extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @NotBlank
    private String kundennummer;
}
