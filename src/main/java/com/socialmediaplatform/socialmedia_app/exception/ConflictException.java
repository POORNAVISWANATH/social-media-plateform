package com.socialmediaplatform.socialmedia_app.exception;

//this class combines the Email and username already exist Exception
public class ConflictException extends RuntimeException{
    public ConflictException(String message){
        super(message);
    }
}
