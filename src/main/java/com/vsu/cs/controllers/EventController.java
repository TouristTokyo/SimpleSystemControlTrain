package com.vsu.cs.controllers;

import com.vsu.cs.models.Event;
import com.vsu.cs.dto.EventDto;
import com.vsu.cs.services.EventService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final ModelMapper modelMapper;

    @Autowired
    public EventController(EventService eventService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<EventDto> getEvents() {
        return eventService.getEvents().stream().map(this::convertToEventDto).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid EventDto eventDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        Event event = convertToEvent(eventDto);
        event.setDate(LocalDate.now());
        eventService.addEvent(event);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<HttpStatus> clear() {
        eventService.clear();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private EventDto convertToEventDto(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

    private Event convertToEvent(EventDto eventDto) {
        return modelMapper.map(eventDto, Event.class);
    }
}
