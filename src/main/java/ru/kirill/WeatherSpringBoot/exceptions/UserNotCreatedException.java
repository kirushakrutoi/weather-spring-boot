package ru.kirill.WeatherSpringBoot.exceptions;

public class UserNotCreatedException extends RuntimeException{
    public UserNotCreatedException(String msg){
        super(msg);
    }
}
