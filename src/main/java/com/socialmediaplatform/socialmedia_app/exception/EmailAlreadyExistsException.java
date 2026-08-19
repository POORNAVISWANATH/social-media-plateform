package com.socialmediaplatform.socialmedia_app.exception;

public class EmailAlreadyExistsException extends ConflictException{
    public EmailAlreadyExistsException(String email){
        super("Email " + email + " already exists");
    }
}
