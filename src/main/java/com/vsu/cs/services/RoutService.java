package com.vsu.cs.services;

import com.vsu.cs.models.Rout;
import com.vsu.cs.repositories.RoutRepository;
import com.vsu.cs.utils.execptions.RoutNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoutService {
    private final RoutRepository routRepository;

    @Autowired
    public RoutService(RoutRepository routRepository) {
        this.routRepository = routRepository;
    }

    public List<Rout> getRoutes() {
        return routRepository.findAll();
    }

    public Rout getRout(BigInteger id) {
        return routRepository.findById(id).orElseThrow(RoutNotFoundException::new);
    }

    @Transactional
    public void addRout(Rout rout) {
        routRepository.save(rout);
    }

    @Transactional
    public void delete(BigInteger id) {
        routRepository.deleteById(id);
    }
}
