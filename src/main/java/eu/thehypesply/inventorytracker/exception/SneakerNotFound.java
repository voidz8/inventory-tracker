package eu.thehypesply.inventorytracker.exception;

public class SneakerNotFound extends RuntimeException{

    public SneakerNotFound() {
        super("Sneaker not found. Check the pid and try again.");
    }

    public SneakerNotFound(String message) {
        super(message);
    }
}
