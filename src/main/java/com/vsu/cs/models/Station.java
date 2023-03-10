package com.vsu.cs.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Table(name = "stations")
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "town")
    @NotNull
    private String town;

}
