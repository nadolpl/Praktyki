package pl.sensilabs.praktyki.bookCategory;

class BookCategoryNotFoundException extends RuntimeException {
    BookCategoryNotFoundException(int id) {
        super("Could not find book type with id " + id);
    }
}
