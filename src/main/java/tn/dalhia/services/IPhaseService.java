package tn.dalhia.services;

import tn.dalhia.entities.Phase;

import java.util.List;

public interface IPhaseService {

    List<Phase> getAll();
    Phase add(Phase phase);
    Phase modify(Phase phase, Long id);
    Phase get(Long id);
    boolean delete(Long id);
}
