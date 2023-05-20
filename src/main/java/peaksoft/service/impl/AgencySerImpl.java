package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Agency;
import peaksoft.repository.AgencyRep;
import peaksoft.service.AgencySer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencySerImpl implements AgencySer {
    private final AgencyRep agencyRep;

    @Override
    public void saveAgency(Agency agency) {
        agencyRep.saveAgency(agency);
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyRep.getAgencyById(id);
    }

    @Override
    public List<Agency> getAllAgencies(String word) {
        return agencyRep.getAllAgencies(word);
    }

    @Override
    public void updateAgency(Long id, Agency agency) {
        agencyRep.updateAgency(id, agency);
    }

    @Override
    public void deleteAgencyById(Long id) {
        agencyRep.deleteAgencyById(id);
    }

    @Override
    public List<Agency> searchAgency(String word) {
        return agencyRep.searchAgency(word);
    }
}
