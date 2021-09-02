package ua.kirilogrecha.backend.api.exceptions;

public class IncorrectEnteringException extends Exception {
    public IncorrectEnteringException() {
        super("Incorrect enter amount of product");
    }
}
