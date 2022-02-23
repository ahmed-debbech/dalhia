package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Resources;
import tn.dalhia.repositories.ResourcesRepository;
import tn.dalhia.services.IResourcesService;

import java.util.List;

@Service
@Slf4j
public class ResourcesService implements IResourcesService {

    @Autowired
    ResourcesRepository resourcesRepository;

    @Override
    public List<Resources> getAll(){
        return resourcesRepository.findAll();
    }

    @Override
    public Resources add(Resources resources){

        return resourcesRepository.save(resources);
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
            resourcesRepository.save(r);
            return true;
        }
        return false;
    }
}
