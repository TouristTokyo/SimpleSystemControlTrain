package com.vsu.cs.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Table(name = "routes")
@Data
public class Rout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    @NotNull
    private String name;


    @ManyToOne
    @JoinColumn(name = "start_station_id", referencedColumnName = "id")
    @NotNull
    private Station stationStart;

    @ManyToOne
    @JoinColumn(name = "end_station_id", referencedColumnName = "id")
    @NotNull
    private Station stationEnd;
}
