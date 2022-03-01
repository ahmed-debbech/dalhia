package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Phase;
import tn.dalhia.entities.Resources;
import tn.dalhia.repositories.PhaseRepository;
import tn.dalhia.repositories.ResourcesRepository;
import tn.dalhia.services.IResourcesService;

import java.util.List;

@Service
@Slf4j
public class ResourcesService implements IResourcesService {

    @Autowired
    ResourcesRepository resourcesRepository;
    @Autowired
    PhaseRepository phaseRepository;

    @Override
    public List<Resources> getAllByPhase(Long id){
        Phase ph =phaseRepository.findById(id).orElse(null);
        if(ph == null)
            return null;

        return ph.getResources();
    }


    @Override
    public Resources add(Resources resources, Long id){
        Phase ph = phaseRepository.findById(id).orElse(null);
        if (ph == null)
            return null;

        ph.getResources().add(resources);
        phaseRepository.save(ph);
        return resources;
    }

    @Override
    public Resources modify(Resources resources , Long id){
        Resources r1 = this.get(id);
        if(r1 == null){
            return null;
        }

        return resourcesRepository.save(resources);
    }

    @Override
    public Resources get(Long id){
        return resourcesRepository.findById(id).orElse(null);
    }

    @Override
    public  boolean delete(Long id){
        Resources r = resourcesRepository.findById(id).orElse(null);
        if(r != null){
            resourcesRepository.delete(r);
            return true;
        }
        return false;
    }
}
