package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Answer;
import tn.dalhia.services.IAnswerService;

import java.util.List;


@RestController
@RequestMapping("/course/answer")
@RequiredArgsConstructor
@Slf4j
public class AnswerController {

   @Autowired
    private IAnswerService answerService;

    @GetMapping("/answerList")
    public ResponseEntity<List<Answer>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                answerService.getAll()
        );
    }
    @PostMapping("/add")
    public ResponseEntity<Answer> add(@RequestBody Answer answer){
        Answer a = answerService.add(answer);
        return ResponseEntity.status(HttpStatus.OK).body(
                a
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Answer> modify(@RequestBody Answer answer, @PathVariable("id") Long id){
        Answer answer1 = answerService.modify(answer, id);
        if(answer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                answer1
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Answer> get(@PathVariable("id") Long id){
        Answer a1 = answerService.get(id);
        if(a1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                a1
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        boolean b = answerService.delete(id);
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
