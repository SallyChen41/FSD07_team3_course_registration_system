package com.fsd07team3.CourseRegistrationSystem.config;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
