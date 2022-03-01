package tn.dalhia.services;

import tn.dalhia.entities.Phase;

import java.util.List;

public interface IPhaseService {

    List<Phase> getAll();
    Phase add(Phase phase , Long id);
    Phase modify(Phase phase, Long id);
    Phase get(Long id);
    List<Phase> getAllByCourse (Long id);
    boolean delete(Long id);
}
