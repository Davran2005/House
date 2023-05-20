package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.exception.MyException;
import peaksoft.model.Agency;
import peaksoft.repository.AgencyRep;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
//@RequiredArgsConstructor
public class AgencyRepImpl implements AgencyRep {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void updateAgency(Long id, Agency agency) {
        try {
            boolean isEmpty = false;
            for (Agency agency1 : entityManager.createQuery("from Agency a", Agency.class).getResultList()) {
                if (Objects.equals(agency1.getId(), id)) {
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty) {
                Agency agency1 = entityManager.find(Agency.class, id);
                agency1.setName(agency.getName());
                agency1.setCountry(agency.getCountry());
                agency1.setPhoneNumber(agency.getPhoneNumber());
                agency1.setEmail(agency.getEmail());
                agency1.setPhoto(agency.getPhoto());
                entityManager.merge(agency1);
            } else {
                throw new MyException("Agency by ID : " + id + " not found!");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveAgency(Agency agency) {
        entityManager.persist(agency);
    }

    @Override
    public Agency getAgencyById(Long id) {
        try {
            Agency agency = entityManager.find(Agency.class, id);
            if (agency.getId().equals(id)) {
                return agency;
            } else {
                throw new MyException("Agent of this" + id + "was not found");
            }
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Agency> getAllAgencies(String word) {
        if(word == null || word.isEmpty()) return entityManager.createQuery("from Agency a", Agency.class).getResultList();
        else return entityManager.createQuery("select u from Agency u where u.name ilike :word or u.country ilike :word", Agency.class)
                .setParameter("word", "%" + word + "%").setParameter("word", word)
                .getResultList();
    }


    @Override
    public void deleteAgencyById(Long id) {
        try {
            Agency agency = entityManager.find(Agency.class, id);
            if (agency.getId().equals(id)) {
                entityManager.remove(agency);
            } else {
                throw new MyException("Agent of this"
                        + id + "was not found");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Agency> searchAgency(String word) {
        return entityManager.createQuery("select u from Agency u where u.name like :word", Agency.class)
                .setParameter("word", "%" + word + "%").setParameter("word", word).getResultList();
    }
}