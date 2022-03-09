package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Topic;
import tn.dalhia.entities.TopicClaim;
import tn.dalhia.entities.TopicRate;
import tn.dalhia.entities.enumerations.VoteType;
import tn.dalhia.services.ITopicService;
import tn.dalhia.utils.EnumUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
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
    @PostMapping()
    public ResponseEntity<Topic> add(@RequestBody Topic topic){
        Topic top = topicService.add(topic);
        return ResponseEntity.status(HttpStatus.OK).body(
                top
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Topic> modify(@RequestBody Topic topic, @PathVariable("id") Long id){
        Topic top = topicService.modify(topic, id);
        if(topic == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                top
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Topic> get(@PathVariable("id") Long id){
        Topic t1 = topicService.get(id);
        if(t1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                t1
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        boolean b = topicService.delete(id);
        if(!b){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @PutMapping("/{id}/bans/{bool}")
    public ResponseEntity<Boolean> bans(@PathVariable("id") Long id, @PathVariable("bool") boolean bool){
        boolean b = topicService.bans(id, bool);
        if(!b){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @PutMapping("/{id}/rate/{action}")
    public ResponseEntity<TopicRate> rateadd(@PathVariable("id") Long id, @PathVariable("action") VoteType voteType){
        //TODO cancel a vote if it is already there by the same user
        TopicRate vt = topicService.addRate(voteType, id);
        if(vt == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                vt
        );
    }
    @DeleteMapping("/{id}/rate")
    public ResponseEntity<Boolean> deleterate(@PathVariable("id") Long id){
        //TODO get the user id to delete the rate
        boolean b = topicService.RemoveRate(id);
        if(!b){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @GetMapping("/{id}/rate")
    public ResponseEntity<List<TopicRate>> getRate(@PathVariable("id") Long id){
        List<TopicRate> b = topicService.getRate(id);
        if(b == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                b
        );
    }
    @GetMapping("/topicOfTheDay")
    public void topoftheday(){
        topicService.getTopicOfTheDay();
    }
}
