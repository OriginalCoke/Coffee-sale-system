package com.hwy.proj_425.exception;

public class DuplicateIdException extends Exception {
    private static final String DEFAULT_MESSAGE = "Id already exist";

    public DuplicateIdException()
    {
        super(DEFAULT_MESSAGE);
    }
}
