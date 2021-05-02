package at.rt.simple.webshop.core.service.impl;

import at.rt.simple.webshop.core.model.domain.Bestellung;
import at.rt.simple.webshop.core.model.domain.Kunde;
import at.rt.simple.webshop.core.model.dto.KundeDto;
import at.rt.simple.webshop.core.service.api.IBestellungService;
import at.rt.simple.webshop.core.service.api.IKundeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementierung des {@link IKundeService}
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 24.04.2021
 */
@Service
public class KundeService implements IKundeService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IBestellungService bestellungService;

    @Override
    public List<Kunde> listKunde() {
        String select = "select k from Kunde k";
        TypedQuery<Kunde> query = entityManager.createQuery(select, Kunde.class);
        return query.getResultList();
    }

    @Override
    public List<KundeDto> listKundeDto() {
        String select = "select new at.rt.simple.webshop.core.model.dto.KundeDto(k, count(b)) " +
                " from Kunde k left join Bestellung b on b.kunde=k group by k";

        return entityManager.createQuery(select, KundeDto.class).getResultList();
    }

    @Override
    public Kunde getKundeById(Long id) {
        return entityManager.find(Kunde.class, id);
    }

    @Override
    @Transactional
    public Kunde saveKunde(Kunde kunde) {
        if (kunde.getId() == null) {
            entityManager.persist(kunde);
        } else {
            kunde = entityManager.merge(kunde);
        }

        return kunde;
    }

    @Override
    @Transactional
    public void deleteKunde(Kunde kunde) {
        Kunde kundeToDelete = kunde;

        if (!entityManager.contains(kundeToDelete)) {
            kundeToDelete = entityManager.find(Kunde.class, kunde.getId());
        }

        // Bestellungen
        String select = "select b from Bestellung b where b.kunde.id =: id";

        TypedQuery<Bestellung> query = entityManager.createQuery(select, Bestellung.class);
        query.setParameter("id", kunde.getId());

        List<Bestellung> resultList = query.getResultList();

        for (Bestellung bestellung : resultList) {
            bestellungService.deleteBestellung(bestellung);
        }

        entityManager.remove(kundeToDelete);
    }
}