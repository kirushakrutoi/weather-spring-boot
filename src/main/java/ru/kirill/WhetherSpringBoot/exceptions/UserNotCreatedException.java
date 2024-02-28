package ru.kirill.WhetherSpringBoot.exceptions;

public class UserNotCreatedException extends RuntimeException{
    public UserNotCreatedException(String msg){
        super(msg);
    }
}
