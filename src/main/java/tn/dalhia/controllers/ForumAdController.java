package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Channel;
import tn.dalhia.entities.ForumAd;
import tn.dalhia.entities.enumerations.ChannelType;
import tn.dalhia.repositories.ForumAdsRepository;
import tn.dalhia.services.IForumAdService;

import java.util.List;

@RestController
@RequestMapping("/forum/ads")
@RequiredArgsConstructor
@Slf4j
public class ForumAdController {

    @Autowired
    private IForumAdService service;

    @PostMapping()
    public ResponseEntity<ForumAd> add(@RequestBody ForumAd ad){
        ForumAd b = service.add(ad);
        if(b == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                b
        );
    }
    @GetMapping()
    public ResponseEntity<List<ForumAd>> getall(){
        List<ForumAd> b = service.getAll();
        if(b == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                b
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ForumAd> mod(@RequestBody ForumAd ad, @PathVariable("id") Long id){
        ForumAd b = service.modidy(ad, id);
        if(b == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                b
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ForumAd> getone(@PathVariable("id") Long id){
        ForumAd b = service.getOne(id);
        if(b == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                b
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        boolean b = service.delete(id);
        if(!b){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @PostMapping("/{id}/clicks")
    public void clk(@PathVariable("id") Long id){
        service.click(id);
    }
}
