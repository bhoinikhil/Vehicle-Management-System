package com.vehicleManagmentSystem.entity.exception;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.data.crossstore.ChangeSetPersister;

public class UserNotFoundByException extends RuntimeException {
     public UserNotFoundByException(String message) {
        super(message);
    }
}
