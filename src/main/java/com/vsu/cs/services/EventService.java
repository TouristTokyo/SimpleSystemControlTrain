package com.vsu.cs.services;

import com.vsu.cs.models.Event;
import com.vsu.cs.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void clear(){
        eventRepository.deleteAll();
    }

}
