package at.rt.simple.webshop.core.service.impl;

import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.model.domain.Produkt;
import at.rt.simple.webshop.core.model.dto.BestellungDto;
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
    public List<Bestellung> listAlleBestellungen() {
        String select = "select b from Bestellung b";
        TypedQuery<Bestellung> query = entityManager.createQuery(select, Bestellung.class);
        return query.getResultList();
    }

    @Override
    public List<BestellungDto> listBestellungDto(Long id) {
        String select = "select new at.rt.simple.webshop.core.model.dto.BestellungDto(b, count(p)) " +
                "from Bestellung b left join Produkt p on p.bestellung=b where b.kunde.id =:id group by b";

        TypedQuery<BestellungDto> query = entityManager.createQuery(select, BestellungDto.class);
        query.setParameter("id", id);

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

        for (Produkt produkt : resultList) {
            entityManager.remove(produkt);
        }
        entityManager.remove(bestellungToDelete);
    }
}