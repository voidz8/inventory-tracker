package eu.thehypesply.inventorytracker.exception;

public class ImageNotFound extends RuntimeException{
    public ImageNotFound() {
        super();
    }

    public ImageNotFound(String message) {
        super(message);
    }
}
