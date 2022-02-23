package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Topic;
import tn.dalhia.entities.TopicClaim;
import tn.dalhia.repositories.TopicClaimRepository;
import tn.dalhia.services.implementations.TopicClaimService;
import tn.dalhia.services.implementations.TopicService;

import java.util.List;

@RestController
@RequestMapping("/forum/topics")
@RequiredArgsConstructor
@Slf4j
public class TopicClaimController {

    @Autowired
    private TopicClaimService topicClaimService;

    @GetMapping("/{id}/claims")
    public ResponseEntity<List<TopicClaim>> getall(@PathVariable("id") Long id){
        List<TopicClaim> b = topicClaimService.getAll(id);
        if(b == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                b
        );
    }
    @PostMapping("/{id}/claims")
    public ResponseEntity<TopicClaim> add(@RequestBody TopicClaim topicClaim, @PathVariable("id") Long id){
        TopicClaim tc = topicClaimService.add(id, topicClaim);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                tc
        );
    }
}
