package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Quiz;
import tn.dalhia.services.IQuizService;

import java.util.List;


@RestController
@RequestMapping("/course/quiz")
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    @Autowired
    private IQuizService quizService;

    @GetMapping()
    public ResponseEntity<List<Quiz>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                quizService.getAll()
        );
    }
    @PostMapping()
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz){
        Quiz q = quizService.add(quiz);
        return ResponseEntity.status(HttpStatus.OK).body(
                q
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Quiz> modify(@RequestBody Quiz quiz, @PathVariable("id") Long id){
        Quiz quiz1 = quizService.modify(quiz, id);
        if(quiz == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                quiz1
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> get(@PathVariable("id") Long id){
        Quiz q1 = quizService.get(id);
        if(q1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                q1
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        boolean b = quizService.delete(id);
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
