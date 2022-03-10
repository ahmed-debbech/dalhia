package tn.dalhia.services;

import tn.dalhia.entities.Event;

import java.util.Collection;
import java.util.List;

public interface IEventService {
    List<Event> getAll();
    Event add(Event event);
    Event modify(Event event);
    Event get(Long id);
    boolean delete(Long id);
    Collection<Event> list(int limit);
    Event TopEvent();
}
