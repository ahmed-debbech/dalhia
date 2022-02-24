package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.CourseCategory;
import tn.dalhia.services.ICourseCategoryService;

import java.util.List;


@RestController
@RequestMapping("/course/courseCategory")
@RequiredArgsConstructor
@Slf4j

public class CourseCategoryController {
    @Autowired
    private ICourseCategoryService courseCategoryService;

    @GetMapping("/coursesList")
    public ResponseEntity<List<CourseCategory>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                courseCategoryService.getAll()
        );
    }
    @PostMapping("/add")
    public ResponseEntity<CourseCategory> add(@RequestBody CourseCategory courseCategory){
        CourseCategory c = courseCategoryService.add(courseCategory);
        return ResponseEntity.status(HttpStatus.OK).body(
                c
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseCategory> modify(@RequestBody CourseCategory courseCategory, @PathVariable("id") Long id){
        CourseCategory courseCategory1 = courseCategoryService.modify(courseCategory, id);
        if(courseCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                courseCategory1
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseCategory> get(@PathVariable("id") Long id){
        CourseCategory c1 = courseCategoryService.get(id);
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
        boolean b = courseCategoryService.delete(id);
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
