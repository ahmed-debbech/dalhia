package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Event;
import tn.dalhia.response.EventResponse;
import tn.dalhia.services.implementations.EventService;


import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;


//    @GetMapping("/list")
//    public ResponseEntity<EventResponse> getEvents(){
//
//        return ResponseEntity.ok(
//                EventResponse.builder()
//                        .timeStamp(now())
//                        .data(Map.of("Events",eventService.list(30)))
//                        .message("Event retrieved")
//                        .status(OK)
//                        .statusCode(OK.value())
//                        .build()
//        );
//    }



    @GetMapping("/list")
    public ResponseEntity<List<Event>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                eventService.getAll()
        );
    }
    @GetMapping("/top-event")
    public ResponseEntity<Event> getTop(){
        return ResponseEntity.status(HttpStatus.OK).body(
                eventService.TopEvent()
        );
    }
//    @PostMapping("/save")
//    public ResponseEntity<EventResponse> addEvent(@RequestBody @Valid Event event){
//        return ResponseEntity.ok(
//                EventResponse.builder()
//                        .timeStamp(now())
//                        .data(Map.of("Event",eventService.add(event)))
//                        .message("Event created")
//                        .status(CREATED)
//                        .statusCode(CREATED.value())
//                        .build()
//        );
//    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EventResponse> getSingleEvent(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                EventResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("Events",eventService.get(id)))
                        .message("Event retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EventResponse> deleteEvent(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                EventResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted",eventService.delete(id)))
                        .message("Event deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Event> add(@RequestBody Event event){
        Event e = eventService.add(event);
        if(e == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                e
        );
    }
    //http://localhost:8089/api/v1/events/get/18
    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable("id") Long id){
        Event e = eventService.get(id);
        if(e == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                e
        );
    }





/*

    @GetMapping(value = "/list")
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

  @Autowired
    IEventService eventService;


    @GetMapping("/list")
    public ResponseEntity<List<Event>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(
                eventService.getAll()
        );
    }
    @GetMapping("/single/{id}")
    public ResponseEntity<Event> get(@PathVariable("id") Long id){
        Event c1 = eventService.get(id);
        if(c1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                c1
        );
    }
    @PostMapping("/add")
    public ResponseEntity<Event> add(@RequestBody Event event){
        Event c = eventService.add(event);
        return ResponseEntity.status(HttpStatus.OK).body(
                c
        );
    }*/

}