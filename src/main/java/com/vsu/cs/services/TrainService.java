package com.vsu.cs.services;

import com.vsu.cs.models.Rout;
import com.vsu.cs.models.Train;
import com.vsu.cs.repositories.TrainRepository;
import com.vsu.cs.utils.execptions.TrainNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TrainService {
    private final TrainRepository trainRepository;

    @Autowired

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public List<Train> getTrains() {
        return trainRepository.findAll();
    }

    public Train getTrain(BigInteger id) {
        return trainRepository.findById(id).orElseThrow(TrainNotFoundException::new);
    }

    @Transactional
    public void addTrain(Train train) {
        if (train.getSpeed() == null) {
            train.setSpeed(0.0);
        }
        if (train.getIsBroken() == null) {
            train.setIsBroken(false);
        }
        trainRepository.save(train);
    }

    @Transactional
    public void delete(BigInteger id) {
        trainRepository.deleteById(id);
    }

    @Transactional
    public void update(BigInteger id, Train train) {
        if (train.getSpeed() == null) {
            train.setSpeed(0.0);
        }
        if (train.getIsBroken() == null) {
            train.setIsBroken(false);
        }
        train.setId(id);
        trainRepository.save(train);
    }

    public List<Train> getTrainsByRout(Rout rout) {
        return trainRepository.findByRout(rout);
    }

}
