package com.vsu.cs.utils.execptions;

public class StationNotFoundException extends RuntimeException {
    public StationNotFoundException(String message) {
        super(message);
    }
}
