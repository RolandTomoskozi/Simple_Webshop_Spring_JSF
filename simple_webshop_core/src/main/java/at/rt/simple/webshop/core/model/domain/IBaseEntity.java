package at.rt.simple.webshop.core.model.domain;

/**
 * Basis Interface fuer alle JPA Entities. Entities muesen eine Methode zum Liefern der ID zur Verfuegung stellen.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
public interface IBaseEntity {
    /**
     * ID des Entity.
     *
     * @return ID
     */
    Long getId();
}
