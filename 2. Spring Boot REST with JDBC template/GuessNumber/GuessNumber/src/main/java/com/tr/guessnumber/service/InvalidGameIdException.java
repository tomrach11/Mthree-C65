package com.tr.guessnumber.service;

public class InvalidGameIdException extends Exception {
    public InvalidGameIdException(String message) {
        super(message);
    }
    public InvalidGameIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
