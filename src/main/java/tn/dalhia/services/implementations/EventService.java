package tn.dalhia.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Event;

import tn.dalhia.entities.enumerations.EventStatus;
import tn.dalhia.repositories.DonationRepository;
import tn.dalhia.repositories.EventRepository;
import tn.dalhia.services.IEventService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventService implements IEventService {
    private final EventRepository eventRepository;
    private int limit;
    private final DonationRepository donationRepository;

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event add(Event event) {
        log.info("Adding new Event: {}",event.getTitle());
        event.setImage(setEventImageURL());
        event.setStartDate(LocalDateTime.now());
        event.setViews(0);
        event.setCollectedAmount(0.0);
        event.setGoalAmount(50.0);
        event.setIsPublished(0);
        event.setEventStatus(EventStatus.SCHEDULED);
        return eventRepository.save(event);
    }

    private String setEventImageURL() {
        return null;
    }

    @Override
    public Event modify(Event event) {
        log.info("Updating Event:{}",event.getTitle());
        return eventRepository.save(event);
    }

    @Override
    public Event get(Long id) {
        log.info("Fetching Event by id:{}",id);
        Event e=eventRepository.findById(id).orElse(null);
        e.setViews(e.getViews()+1);
        if (LocalDateTime.now().isAfter(e.getEndDate()) && e.getEventStatus()!=EventStatus.DONE){
            e.setEventStatus(EventStatus.DONE);
        }
        return e;
    }

    @Override
    public boolean delete(Long id) {
        log.info("Deleting Event by ID {}",id);
        eventRepository.deleteById(id);
        return TRUE;
    }

    @Override
    public Collection<Event> list(int limit) {
        log.info("Fetching all events");
        return eventRepository.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Event TopEvent() {
       Long id = eventRepository.getMostEventSuccess();
        return eventRepository.findById(id).orElse(null);
    }

    public int getEventRate(Long id){
        return 0;
    }

}
