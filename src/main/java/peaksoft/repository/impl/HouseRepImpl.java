package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.exception.MyException;
import peaksoft.model.Agency;
import peaksoft.model.Booking;
import peaksoft.model.House;
import peaksoft.repository.HouseRep;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class HouseRepImpl implements HouseRep {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveHouse(House house) {
        entityManager.persist(house);
    }

    @Override
    public List<House> getAllHouses(String word) {
        if (word == null || word.isEmpty()) {
        return entityManager.createQuery("from House a", House.class).getResultList();
        } else {
            return entityManager.createQuery("select u from House u where u.address ilike :word or u.country ilike :word ", House.class)
                    .setParameter("word", "%" + word + "%").setParameter("word", word)
                    .getResultList();
        }
    }
    @Override
    public House getHouseById(Long id) {
        try {
            House house = entityManager.find(House.class, id);
            if (house.getId().equals(id)) {
                return house;
            } else {
                throw new MyException("House of this" + id + "was not found");
            }
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateHouseById(Long id, House house) {
        try {
            boolean isEmpty = false;
            for (House house1 : entityManager.createQuery("from House a", House.class).getResultList()) {
                if (Objects.equals(house1.getId(), id)) {
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty) {
                House house1 = entityManager.find(House.class, id);
                house1.setAddress(house.getAddress());
                house1.setPrice(house.getPrice());
                house1.setRoom(house.getRoom());
                house1.setCountry(house.getCountry());
                house1.setDescription(house.getDescription());
                house1.setRoom(house.getRoom());
                entityManager.merge(house1);
            } else {
                throw new MyException("House by ID : " + id + " not found!");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteHouseById(Long id) {
        try {
            House house = entityManager.find(House.class, id);
            if (house.getId().equals(id)) {
                entityManager.remove(house);
            } else {
                throw new MyException("House of this"
                        + id + "was not found");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<House> sortHouseByHouseType(String ascOrDesc) {
        try {
            if (ascOrDesc.equals("asc")) {
                return entityManager.createQuery("from House a order by houseType asc", House.class).getResultList();
            } else if (ascOrDesc.equals("desc")) {
                return entityManager.createQuery("from House a order by houseType desc ", House.class).getResultList();
            }else {
                throw new MyException("select only asc desc");
            }
        }catch (MyException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<House> searchHouse(String word) {
        return entityManager.createQuery("select u from House u where u.address like :word", House.class)
                .setParameter("word", "%" + word + "%").setParameter("word", word).getResultList();
    }

}
