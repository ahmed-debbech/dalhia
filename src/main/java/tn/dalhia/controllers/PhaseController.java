package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Phase;
import tn.dalhia.services.IPhaseService;

import java.util.List;


@RestController
@RequestMapping("/course/phase")
@RequiredArgsConstructor
@Slf4j
public class PhaseController {

    @Autowired
    private IPhaseService phaseService;

    @GetMapping("/phaseList")
    public ResponseEntity<List<Phase>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                phaseService.getAll()
        );
    }
    @PostMapping("/{id}/add")
    public ResponseEntity<Phase> add(@RequestBody Phase phase, @PathVariable("id") Long id){
        Phase ph = phaseService.add(phase,id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ph
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Phase> modify(@RequestBody Phase phase, @PathVariable("id") Long id){
        Phase phase1 = phaseService.modify(phase, id);
        if(phase == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                phase1
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Phase> get(@PathVariable("id") Long id){
        Phase ph1 = phaseService.get(id);
        if(ph1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                ph1
        );
    }

    @GetMapping("/listPhasesByCourse/{id}")
    public ResponseEntity<List<Phase>> getAllByCourse(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                phaseService.getAllByCourse(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        boolean b = phaseService.delete(id);
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
