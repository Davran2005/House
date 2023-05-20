package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Agency;
import peaksoft.model.House;

import java.util.List;

public interface HouseSer {
    void saveHouse(Long agencyId, House house);
    List<House> getAllHouses(Long agencyId,String word);
    House getHouseById(Long id);
    void updateHouseById(Long id, House house);
    void deleteHouseById(Long id);

    List<House> sortHouseByHouseType(String ascOrDesc);
    List<House> searchHouse(String word);
}
