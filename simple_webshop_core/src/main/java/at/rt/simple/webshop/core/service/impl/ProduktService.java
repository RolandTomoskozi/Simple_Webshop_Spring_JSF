package at.rt.simple.webshop.core.service.impl;

import at.rt.simple.webshop.core.model.domain.Produkt;
import at.rt.simple.webshop.core.model.dto.BestellungDto;
import at.rt.simple.webshop.core.service.api.IProduktService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementierung des {@link IProduktService}
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
@Service
public class ProduktService implements IProduktService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Produkt> listAlleProdukte() {
        String select = "select p from Produkt p";
        TypedQuery<Produkt> query = entityManager.createQuery(select, Produkt.class);
        return query.getResultList();
    }

    @Override
    public List<Produkt> listProdukte(String bestellnummer) {
        String select = "select p from Produkt p where p.bestellnummer =: bestellnummer";
        TypedQuery<Produkt> query = entityManager.createQuery(select, Produkt.class);
        query.setParameter("bestellnummer", bestellnummer);
        return query.getResultList();
    }

    @Override
    public List<Produkt> listProdukte(List<BestellungDto> bestellnummerList) {
        List<String> collectBestellnummer = bestellnummerList
                .stream().map(dto -> dto.getBestellung().getBestellnummer()).collect(Collectors.toList());

        String select = "select p from Produkt p where p.bestellnummer in :collectBestellnummer";

        TypedQuery<Produkt> query = entityManager.createQuery(select, Produkt.class);
        query.setParameter("collectBestellnummer", collectBestellnummer);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Produkt saveProdukt(Produkt produkt) {
        if (produkt.getId() == null) {
            entityManager.persist(produkt);
        } else {
            produkt = entityManager.merge(produkt);
        }

        return produkt;
    }

    @Override
    @Transactional
    public void deleteProdukt(Produkt produkt) {
        Produkt produktToDelete = produkt;
        if (!entityManager.contains(produktToDelete)) {
            produktToDelete = entityManager.find(Produkt.class, produkt.getId());
        }

        entityManager.remove(produktToDelete);
    }
}
