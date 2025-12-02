package com.example.notesbackend.notes.service;

/**
 * Simple runtime exception to indicate a resource was not found.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
