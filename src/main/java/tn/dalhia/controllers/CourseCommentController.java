package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.CourseComment;
import tn.dalhia.services.ICourseCommentService;

import java.util.List;


@RestController
@RequestMapping("/course/courseComment")
@RequiredArgsConstructor
@Slf4j

public class CourseCommentController {

    @Autowired
    private ICourseCommentService courseCommentService;

    @GetMapping("/coursesList")
    public ResponseEntity<List<CourseComment>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                courseCommentService.getAll()
        );
    }
    @PostMapping("/add")
    public ResponseEntity<CourseComment> add(@RequestBody CourseComment courseComment){
        CourseComment c = courseCommentService.add(courseComment);
        return ResponseEntity.status(HttpStatus.OK).body(
                c
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseComment> modify(@RequestBody CourseComment courseComment, @PathVariable("id") Long id){
        CourseComment courseComment1 = courseCommentService.modify(courseComment, id);
        if(courseComment == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                courseComment1
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseComment> get(@PathVariable("id") Long id){
        CourseComment c1 = courseCommentService.get(id);
        if(c1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                c1
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        boolean b = courseCommentService.delete(id);
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
