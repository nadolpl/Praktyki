package pl.sensilabs.praktyki.exceptions;

public class TypeNotFound extends RuntimeException {
    public TypeNotFound(int id) {
        super("Could not find book type with id " + id);
    }
}
