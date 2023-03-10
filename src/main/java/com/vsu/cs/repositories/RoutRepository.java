package com.vsu.cs.repositories;

import com.vsu.cs.models.Rout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RoutRepository extends JpaRepository<Rout, BigInteger> {
}
