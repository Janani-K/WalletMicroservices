package com.example.walletMicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not find a player with given Player ID")
public class PlayerNotFountException extends Exception{
}
