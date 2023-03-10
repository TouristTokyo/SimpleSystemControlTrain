package com.vsu.cs.services;

import com.vsu.cs.models.Station;
import com.vsu.cs.repositories.StationRepository;
import com.vsu.cs.utils.execptions.StationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StationService {
    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getStations() {
        return stationRepository.findAll();
    }

    @Transactional
    public void addStation(Station station) {
        stationRepository.save(station);
    }

    @Transactional
    public void delete(BigInteger id) {
        stationRepository.deleteById(id);
    }

    public Station getStation(BigInteger id) {
        return stationRepository.findById(id).orElseThrow(StationNotFoundException::new);
    }
}

