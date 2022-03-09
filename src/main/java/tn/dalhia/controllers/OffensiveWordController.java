package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.OffensiveWord;
import tn.dalhia.entities.Topic;
import tn.dalhia.repositories.OffensiveWordRepository;
import tn.dalhia.services.IOffensiveWordService;

import java.util.List;

@RestController
@RequestMapping("/offensive")
@RequiredArgsConstructor
@Slf4j
public class OffensiveWordController {

    @Autowired
    private IOffensiveWordService service;


    @PostMapping("/{word}")
    public ResponseEntity<OffensiveWord> add(@PathVariable("word") String word){
        OffensiveWord top = service.add(word);
        return ResponseEntity.status(HttpStatus.OK).body(
                top
        );
    }
    @GetMapping
    public ResponseEntity<List<OffensiveWord>> get(){
        List<OffensiveWord> top = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                top
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        boolean b = service.delete(id);
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
