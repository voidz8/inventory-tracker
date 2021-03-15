package eu.thehypesply.inventorytracker.exception;

public class ClothingNotFound extends RuntimeException{
    public ClothingNotFound() {super("Clothing not found.");}
    public ClothingNotFound(String message) {super(message);}
}
