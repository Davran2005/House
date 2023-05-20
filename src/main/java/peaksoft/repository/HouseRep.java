package peaksoft.repository;

import peaksoft.model.Agency;
import peaksoft.model.House;

import java.util.List;

public interface HouseRep {
    void saveHouse(House house);
    List<House> getAllHouses(String word);
    House getHouseById(Long id);
    void updateHouseById(Long id, House house);
    void deleteHouseById(Long id);

    List<House> sortHouseByHouseType(String ascOrDesc);
    List<House> searchHouse(String word);
}
