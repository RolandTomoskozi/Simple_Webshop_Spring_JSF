package at.rt.simple.webshop.core.model.domain;

import java.io.Serializable;

/**
 * Basisklasse fuer alle Entities. Implementiert ID/HashCode anhand der ID und toString.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
public abstract class BaseEntity implements Serializable, IBaseEntity {
    @Override
    public String toString() {
        return getClass().getName() + " [id=" + getId() + "]";
    }

    @Override
    public int hashCode() {
        Long id = getId();

        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        if (getId() == null) {
            return other.getId() == null;
        } else if (!getId().equals(other.getId())) {
            return false;
        }
        return true;
    }
}
