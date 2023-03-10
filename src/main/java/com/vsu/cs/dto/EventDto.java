package com.vsu.cs.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EventDto {
    @NotNull
    private String description;

    private Date date;
}
