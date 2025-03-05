package pl.sensilabs.praktyki.exceptions;

public class CategoryNameExist extends RuntimeException{
    public CategoryNameExist(String categoryName) {super("BookCategory with name " + categoryName + " already exists");}
}
