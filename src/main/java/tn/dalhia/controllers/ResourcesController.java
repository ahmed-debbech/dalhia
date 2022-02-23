package tn.dalhia.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Resources;
import tn.dalhia.services.IResourcesService;

import java.util.List;


@RestController
@RequestMapping("/course/resources")
@RequiredArgsConstructor
@Slf4j
public class ResourcesController {

    @Autowired
    private IResourcesService resourcesService;

    @GetMapping()
    public ResponseEntity<List<Resources>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                resourcesService.getAll()
        );
    }
    @PostMapping()
    public ResponseEntity<Resources> add(@RequestBody Resources resources){
        Resources r = resourcesService.add(resources);
        return ResponseEntity.status(HttpStatus.OK).body(
                r
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Resources> modify(@RequestBody Resources resources, @PathVariable("id") Long id){
        Resources resources1 = resourcesService.modify(resources, id);
        if(resources == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                resources1
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Resources> get(@PathVariable("id") Long id){
        Resources r1 = resourcesService.get(id);
        if(r1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                r1
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        boolean b = resourcesService.delete(id);
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
