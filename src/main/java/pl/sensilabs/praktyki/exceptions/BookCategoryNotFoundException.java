package pl.sensilabs.praktyki.exceptions;

public class BookCategoryNotFoundException extends RuntimeException {
    public BookCategoryNotFoundException(int id) {
        super("Could not find book type with id " + id);
    }
}
