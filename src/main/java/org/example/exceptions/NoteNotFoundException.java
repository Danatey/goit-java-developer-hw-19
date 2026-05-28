package org.example.exceptions;

public class NoteNotFoundException
        extends RuntimeException {

    public NoteNotFoundException(Long id) {
        super("Note not found: " + id);
    }
}
