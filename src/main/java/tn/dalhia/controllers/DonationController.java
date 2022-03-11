package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Donation;
import tn.dalhia.entities.Event;
import tn.dalhia.services.implementations.DonationService;
import tn.dalhia.services.implementations.EventService;

import java.util.List;

@RestController
@RequestMapping("events/")
@RequiredArgsConstructor
@Slf4j
public class DonationController {
    private final DonationService donationService;
    @PostMapping("/{event-id}/donate")
    public ResponseEntity<Donation> add(@RequestBody Donation donation,@PathVariable("event-id") Long eventId){
        log.info("Adding Donation: {}");
        Donation d = donationService.add(donation,eventId);
        return ResponseEntity.status(HttpStatus.OK).body(
                d
        );
    }
    @GetMapping("donations/list")
    public ResponseEntity<List<Donation>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                donationService.getAll()
        );
    }
    @GetMapping("donation/{id}")
    public ResponseEntity<Donation> get(@PathVariable("id") Long id){
        Donation d = donationService.get(id);
        if(d == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                d
        );
    }
}
