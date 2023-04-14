package com.vsu.cs.controllers;

import com.vsu.cs.dto.TrainCreateDto;
import com.vsu.cs.dto.TrainDto;
import com.vsu.cs.models.Event;
import com.vsu.cs.models.Train;
import com.vsu.cs.services.EventService;
import com.vsu.cs.services.RoutService;
import com.vsu.cs.services.StationService;
import com.vsu.cs.services.TrainService;
import com.vsu.cs.utils.ErrorResponse;
import com.vsu.cs.utils.execptions.TrainNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trains")
public class TrainController {
    private final TrainService trainService;
    private final EventService eventService;
    private final StationService stationService;
    private final RoutService routService;
    private final ModelMapper modelMapper;

    @Autowired
    public TrainController(TrainService trainService, EventService eventService, StationService stationService, RoutService routService, ModelMapper mapper) {
        this.trainService = trainService;
        this.eventService = eventService;
        this.stationService = stationService;
        this.routService = routService;
        this.modelMapper = mapper;
    }

    @GetMapping
    public List<TrainDto> getTrains() {
        return trainService.getTrains().stream().map(this::convertToTtrainDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TrainDto getTrain(@PathVariable("id") BigInteger id) {
        return convertToTtrainDto(trainService.getTrain(id));
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid TrainCreateDto trainDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        trainService.addTrain(convertToTtrain(trainDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") BigInteger id) {
        trainService.getTrain(id);
        trainService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") BigInteger id,
                                             @RequestBody @Valid TrainCreateDto trainDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Train trainLast = trainService.getTrain(id);
        if (trainLast.getIsBroken() != trainDto.getIsBroken()) {
            if (trainDto.getIsBroken()) {
                eventService.addEvent(new Event("train (" + trainDto.getName() + ") " + "broke down"));
            } else {
                eventService.addEvent(new Event("train (" + trainDto.getName() + ")" + "  is fixed"));
            }
        }
        if (!trainDto.getCurrentStationId().equals(trainLast.getCurrentStation().getId())) {
            eventService.addEvent(new Event("train (" + trainLast.getName() + ")" +
                    "left the station (" + trainLast.getCurrentStation().getName() + ")"));
        }
        trainService.update(id, convertToTtrain(trainDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(TrainNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                LocalDate.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private TrainDto convertToTtrainDto(Train train) {
        return modelMapper.map(train, TrainDto.class);
    }

    private Train convertToTtrain(TrainCreateDto trainDto) {
        Train train = new Train();
        train.setName(trainDto.getName());
        train.setSpeed(trainDto.getSpeed());
        train.setIsBroken(trainDto.getIsBroken());
        train.setCurrentStation(stationService.getStation(trainDto.getCurrentStationId()));
        train.setRout(routService.getRout(trainDto.getRoutId()));

        return train;
    }
}
