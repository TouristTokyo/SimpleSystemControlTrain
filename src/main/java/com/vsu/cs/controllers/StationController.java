package com.vsu.cs.controllers;

import com.vsu.cs.dto.StationDto;
import com.vsu.cs.models.Station;
import com.vsu.cs.services.StationService;
import com.vsu.cs.utils.ErrorResponse;
import com.vsu.cs.utils.execptions.StationNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stations")
public class StationController {
    private final StationService stationService;
    private final ModelMapper modelMapper;

    @Autowired
    public StationController(StationService stationService, ModelMapper modelMapper) {
        this.stationService = stationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<StationDto> getStations() {
        return stationService.getStations().stream().map(this::convertToStationDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StationDto getStation(@PathVariable("id") BigInteger id) {
        return convertToStationDto(stationService.getStation(id));
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid StationDto stationDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        stationService.addStation(convertToStation(stationDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") BigInteger id) {
        stationService.getStation(id);
        stationService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(StationNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                LocalDate.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private StationDto convertToStationDto(Station station) {
        return modelMapper.map(station, StationDto.class);
    }

    private Station convertToStation(StationDto stationDto){
        return modelMapper.map(stationDto, Station.class);
    }
}
