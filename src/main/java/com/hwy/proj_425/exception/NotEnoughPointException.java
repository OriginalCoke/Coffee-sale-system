package com.hwy.proj_425.exception;

public class NotEnoughPointException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough point , customer must hava at least 25 point";

    public NotEnoughPointException() {
        super(DEFAULT_MESSAGE);
    }

}
