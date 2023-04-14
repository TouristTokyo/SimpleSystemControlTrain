package com.vsu.cs.utils.execptions;

public class RoutNotFoundException extends RuntimeException{
    public RoutNotFoundException(String message) {
        super(message);
    }
}
