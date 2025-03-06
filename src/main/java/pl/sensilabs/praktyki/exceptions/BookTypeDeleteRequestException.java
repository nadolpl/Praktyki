package pl.sensilabs.praktyki.exceptions;

public class BookTypeDeleteRequestException extends RuntimeException {
    public BookTypeDeleteRequestException(String message) {
        super(message);
    }
}
