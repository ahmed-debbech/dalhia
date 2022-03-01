package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Question;
import tn.dalhia.services.IQuestionService;

import java.util.List;


@RestController
@RequestMapping("/course/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping("/{id}/questionListByQuiz")
    public ResponseEntity<List<Question>> getAllByQuiz(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                questionService.getAllByQuiz(id)
        );
    }
    @PostMapping("/add")
    public ResponseEntity<Question> add(@RequestBody Question question){
        Question q = questionService.add(question);
        return ResponseEntity.status(HttpStatus.OK).body(
                q
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Question> modify(@RequestBody Question question, @PathVariable("id") Long id){
        Question question1 = questionService.modify(question, id);
        if(question == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                question1
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> get(@PathVariable("id") Long id){
        Question q1 = questionService.get(id);
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
        boolean b = questionService.delete(id);
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
