package tn.dalhia.services;

import tn.dalhia.entities.Place;

import java.util.List;

public interface IPlaceService {
    List<Place> getAll();
    Place add(Place place);
    Place modify(Place place, Long id);
    Place get(Long id);
    boolean delete(Long id);
}
