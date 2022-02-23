package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Place;
import tn.dalhia.services.IPlaceService;

import java.util.List;


@RestController
@RequestMapping("/course/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    @Autowired
    private IPlaceService placeService;

    @GetMapping()
    public ResponseEntity<List<Place>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                placeService.getAll()
        );
    }
    @PostMapping()
    public ResponseEntity<Place> add(@RequestBody Place place){
        Place p = placeService.add(place);
        return ResponseEntity.status(HttpStatus.OK).body(
                p
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Place> modify(@RequestBody Place place, @PathVariable("id") Long id){
        Place place1 = placeService.modify(place, id);
        if(place == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                place1
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Place> get(@PathVariable("id") Long id){
        Place p1 = placeService.get(id);
        if(p1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                p1
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        boolean b = placeService.delete(id);
        if(!b){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }

}
