package pl.sensilabs.praktyki.exceptions;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(int id) {
        super("Could not find book type with id " + id);
    }
}
