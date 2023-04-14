package com.vsu.cs.controllers;

import com.vsu.cs.dto.RoutCreateDto;
import com.vsu.cs.dto.RoutDto;
import com.vsu.cs.models.Rout;
import com.vsu.cs.models.Train;
import com.vsu.cs.services.RoutService;
import com.vsu.cs.services.StationService;
import com.vsu.cs.services.TrainService;
import com.vsu.cs.utils.ErrorResponse;
import com.vsu.cs.utils.execptions.RoutNotFoundException;
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
@RequestMapping("/routes")
public class RoutController {

    private final RoutService routService;
    private final TrainService trainService;
    private final StationService stationService;

    private final ModelMapper modelMapper;

    @Autowired
    public RoutController(RoutService routService, TrainService trainService, StationService stationService, ModelMapper modelMapper) {
        this.routService = routService;
        this.trainService = trainService;
        this.stationService = stationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<RoutDto> getRoutes() {
        return routService.getRoutes().stream().map(this::convertToRoutDto).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid RoutCreateDto routDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        routService.addRout(convertToRout(routDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") BigInteger id) {
        List<Train> trains = trainService.getTrainsByRout(routService.getRout(id));
        trains.forEach(train -> train.setCurrentStation(null));
        routService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public RoutDto getRout(@PathVariable("id") BigInteger id) {
        return convertToRoutDto(routService.getRout(id));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(RoutNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                LocalDate.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private RoutDto convertToRoutDto(Rout rout) {
        return modelMapper.map(rout, RoutDto.class);
    }

    private Rout convertToRout(RoutCreateDto routDto) {
        Rout rout = new Rout();
        rout.setName(routDto.getName());
        rout.setStationStart(stationService.getStation(routDto.getStationStartId()));
        rout.setStationEnd(stationService.getStation(routDto.getStationEndId()));

        return rout;
    }
}
