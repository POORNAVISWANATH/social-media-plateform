package com.socialmediaplatform.socialmedia_app.exception;

public class UsernameAlreadyExistsException extends ConflictException{
    public UsernameAlreadyExistsException(String username){

        super("Username " + username + " already exists");
    }
}
