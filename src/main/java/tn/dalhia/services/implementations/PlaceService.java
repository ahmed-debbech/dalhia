package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Course;
import tn.dalhia.entities.Place;
import tn.dalhia.repositories.CertificateRepository;
import tn.dalhia.repositories.CourseRepository;
import tn.dalhia.repositories.PlaceRepository;
import tn.dalhia.services.IPlaceService;

import java.util.List;

@Service
@Slf4j
public class PlaceService implements IPlaceService {
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Place> getAll(){
        return placeRepository.findAll();
    }

    @Override
    public Place add(Place place, Long id){
        Course c = courseRepository.findById(id).orElse(null);
        if(c==null)
            return null;
        if (c.getModality()== "en ligne")
            return null;
        c.getPlaces().add(place);
        courseRepository.save(c);
        return place;
    }

    @Override
    public Place modify(Place place , Long id){
        Place p1 = this.get(id);
        if(p1 == null){
            return null;
        }

        return placeRepository.save(place);
    }

    @Override
    public Place get(Long id){
        return placeRepository.findById(id).orElse(null);
    }

    @Override
    public  boolean delete(Long id){
        Place p = placeRepository.findById(id).orElse(null);
        if(p != null){
            placeRepository.delete(p);
            return true;
        }
        return false;
    }
}
