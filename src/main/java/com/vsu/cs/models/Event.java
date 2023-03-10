package com.vsu.cs.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
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
    private Date date;

    public Event(String description, Date date) {
        this.description = description;
        this.date = date;
    }
}
