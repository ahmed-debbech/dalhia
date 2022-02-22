package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.dalhia.entities.Topic;
import tn.dalhia.services.ITopicService;

import java.util.List;

@RestController
@RequestMapping("/forum/topics")
@RequiredArgsConstructor
@Slf4j
public class TopicController {

    @Autowired
    private ITopicService topicService;

    @GetMapping()
    public ResponseEntity<List<Topic>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                topicService.getAll()
        );
    }
}
