package com.example.demo.domain.exception;

public class DomainException extends RuntimeException {
    DomainException(String message) {
        super(message);
    }

    static String constructMessage(String domain, String message) {
        return "Domain Exception: " + domain + " - " + message;
    }

    public static DomainException throwDomainException(String domain, String message) {
        return new DomainException(constructMessage(domain, message));
    }

}
