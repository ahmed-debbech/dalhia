package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Course;
import tn.dalhia.services.ICourseService;

import java.util.List;


@RestController
@RequestMapping("/course/course")
@RequiredArgsConstructor
@Slf4j

public class CourseController {

    @Autowired
    private ICourseService courseService;

    @GetMapping()
    public ResponseEntity<List<Course>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                courseService.
        );
    }
    @PostMapping()
    public ResponseEntity<Course> add(@RequestBody Course course){
        Course c = courseService.add(course);
        return ResponseEntity.status(HttpStatus.OK).body(
                c
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Certificate> modify(@RequestBody Certificate certificate, @PathVariable("id") Long id){
        Certificate certificate1 = certificateService.modify(certificate, id);
        if(certificate == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                certificate1
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Certificate> get(@PathVariable("id") Long id){
        Certificate c1 = certificateService.get(id);
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
        boolean b = certificateService.delete(id);
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
