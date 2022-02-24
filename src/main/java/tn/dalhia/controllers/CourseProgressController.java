package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.CourseProgress;
import tn.dalhia.services.ICourseProgressService;

import java.util.List;


@RestController
@RequestMapping("/course/courseProgress")
@RequiredArgsConstructor
@Slf4j

public class CourseProgressController {

    @Autowired
    private ICourseProgressService courseProgressService;

    @GetMapping("/courseProgressList")
    public ResponseEntity<List<CourseProgress>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                courseProgressService.getAll()
        );
    }
    @PostMapping("/add")
    public ResponseEntity<CourseProgress> add(@RequestBody CourseProgress courseProgress){
        CourseProgress c = courseProgressService.add(courseProgress);
        return ResponseEntity.status(HttpStatus.OK).body(
                c
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseProgress> get(@PathVariable("id") Long id){
        CourseProgress c1 = courseProgressService.get(id);
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
        boolean b = courseProgressService.delete(id);
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
