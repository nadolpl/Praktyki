package pl.sensilabs.praktyki.exceptions;

public class BookTypeNotFoundException extends RuntimeException {
    public BookTypeNotFoundException(int id) {
        super("Could not find book type with id " + id);
    }
}
