package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.enums.HouseType;
import peaksoft.model.Agency;
import peaksoft.model.House;
import peaksoft.repository.HouseRep;
import peaksoft.service.HouseSer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseSerImpl implements HouseSer {
    private final HouseRep houseRep;

    @Override
    public void saveHouse(Long agencyId, House house) {
        house.setHouseType(HouseType.valueOf(house.getHouseTypeString()));
        houseRep.saveHouse(agencyId, house);
    }

    @Override
    public List<House> getAllHouses(Long agencyId, String word) {
        return houseRep.getAllHouses(agencyId, word);
    }

    @Override
    public House getHouseById(Long id) {
        return houseRep.getHouseById(id);
    }

    @Override
    public void updateHouseById(Long id, House house) {
        houseRep.updateHouseById(id, house);
    }

    @Override
    public void deleteHouseById(Long id) {
        houseRep.deleteHouseById(id);
    }

    @Override
    public List<House> sortHouseByHouseType(String ascOrDesc) {
        return houseRep.sortHouseByHouseType(ascOrDesc);
    }

    @Override
    public List<House> searchHouse(String word) {
        return houseRep.searchHouse(word);
    }
}
