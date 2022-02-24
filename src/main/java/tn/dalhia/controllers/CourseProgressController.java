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
@RequestMapping("/course/courseProgress")
@RequiredArgsConstructor
@Slf4j

public class CourseProgressController {
}
