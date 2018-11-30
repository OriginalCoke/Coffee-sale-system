package com.hwy.proj_425.exception;

public class ExistUserException extends Exception {
    public ExistUserException()
    {
        super("User Already Exits!");
    }
}
