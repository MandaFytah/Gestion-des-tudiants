package com.memoire.Gestion_des_etudiants.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String detail;
    public ErrorDetails(Date timestamp, String message, String detail) {
        super();
        this.timestamp=timestamp;
        this.message=message;
        this.detail=detail;
    }
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
     return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
