package com.vsu.cs.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "description")
    private String description;

    @Column(name = "event_date")
    private LocalDate date;

    public Event(String description) {
        this.description = description;
        this.date = LocalDate.now();
    }
}
