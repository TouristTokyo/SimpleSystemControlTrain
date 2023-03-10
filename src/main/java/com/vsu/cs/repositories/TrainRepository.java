package com.vsu.cs.repositories;

import com.vsu.cs.models.Rout;
import com.vsu.cs.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, BigInteger> {
    List<Train> findByRout(Rout rout);
}
