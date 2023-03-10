package com.vsu.cs.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Table(name = "trains")
@Data
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "speed")
    @NotNull
    private Double speed;

    @Column(name = "is_broken")
    @NotNull
    private Boolean isBroken;

    @ManyToOne
    @JoinColumn(name = "current_station_id", referencedColumnName = "id")
    @NotNull
    private Station currentStation;

    @ManyToOne
    @JoinColumn(name = "rout_id", referencedColumnName = "id")
    @NotNull
    private Rout rout;
}
