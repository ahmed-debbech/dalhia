package tn.dalhia.services;

import tn.dalhia.entities.Resources;

import java.util.List;

public interface IResourcesService {
    List<Resources> getAllByPhase(Long id);
    Resources add(Resources resources , Long id);
    Resources modify(Resources resources, Long id);
    Resources get(Long id);
    boolean delete(Long id);
}
