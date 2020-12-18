package com.tr.guessnumber.service;

public class InValidGuessException extends Exception{
    public InValidGuessException (String message) {
        super(message);
    }
}
