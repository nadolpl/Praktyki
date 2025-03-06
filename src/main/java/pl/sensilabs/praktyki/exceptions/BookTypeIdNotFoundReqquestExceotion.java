package pl.sensilabs.praktyki.exceptions;

public class BookTypeIdNotFoundReqquestExceotion extends RuntimeException {
    public BookTypeIdNotFoundReqquestExceotion(String message) {
        super(message);

    }
    public BookTypeIdNotFoundReqquestExceotion(String message, Throwable cause) {
        super(message, cause);
    }
}

