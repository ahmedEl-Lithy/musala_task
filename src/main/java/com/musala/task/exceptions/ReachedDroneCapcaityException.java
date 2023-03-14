package com.musala.task.exceptions;

public class ReachedDroneCapcaityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ReachedDroneCapcaityException(String msg) {
        super(msg);
    }
}