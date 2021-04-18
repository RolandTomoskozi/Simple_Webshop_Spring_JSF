package at.rt.simple.webshop.core.service.impl;

import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.model.domain.Produkt;
import at.rt.simple.webshop.core.model.dto.BestellungInfoDto;
import at.rt.simple.webshop.core.service.api.IBestellungService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementierung des {@link IBestellungService}
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
@Service
public class BestellungService implements IBestellungService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Bestellung> listBestellung() {
        String select = "select b from Bestellung b order by b.datum";
        TypedQuery<Bestellung> query = entityManager.createQuery(select, Bestellung.class);
        return query.getResultList();
    }

    @Override
    public Bestellung getBestellungById(Long id) {
        return entityManager.find(Bestellung.class, id);
    }

    @Override
    @Transactional
    public Bestellung saveBestellung(Bestellung bestellung) {
        if (bestellung.getId() == null) {
            entityManager.persist(bestellung);
        } else {
            bestellung = entityManager.merge(bestellung);
        }
        return bestellung;
    }

    @Override
    @Transactional
    public void deleteBestellung(Bestellung bestellung) {
        Bestellung bestellungToDelete = bestellung;
        if (!entityManager.contains(bestellungToDelete)) {
            bestellungToDelete = entityManager.find(Bestellung.class, bestellung.getId());
        }

        String select = "select p from Produkt p where p.bestellnummer =: bestellnummer";
        TypedQuery<Produkt> query = entityManager.createQuery(select, Produkt.class);
        query.setParameter("bestellnummer", bestellung.getBestellnummer());
        List<Produkt> resultList = query.getResultList();

        for (Produkt produkt :resultList) {
            entityManager.remove(produkt);
        }
        entityManager.remove(bestellungToDelete);
    }

    @Override
    public List<BestellungInfoDto> getBestellungInfo() {
        String select = "select new at.rt.simple.webshop.core.model.dto.BestellungInfoDto(b, count(p)) " +
                "from Bestellung b, Produkt p where p.bestellung=b group by b order by b.bezeichnung";
        return entityManager.createQuery(select, BestellungInfoDto.class).getResultList();
    }
}