package peaksoft.service;

import peaksoft.model.Agency;

import java.util.List;

public interface AgencySer {
    void saveAgency(Agency agency);
    Agency getAgencyById(Long id);
    List<Agency> getAllAgencies(String word);
    void updateAgency(Long id,Agency agency);
    void deleteAgencyById(Long id);
    List<Agency> searchAgency(String word);
}
