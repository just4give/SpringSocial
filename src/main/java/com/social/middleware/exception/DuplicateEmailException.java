package com.social.middleware.exception;

/**
 * Created by mithundas on 11/30/14.
 */
public class DuplicateEmailException extends Exception {


    public DuplicateEmailException(String message) {
        super(message);
    }
}
