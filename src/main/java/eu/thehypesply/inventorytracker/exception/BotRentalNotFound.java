package eu.thehypesply.inventorytracker.exception;

public class BotRentalNotFound extends RuntimeException{
    public BotRentalNotFound() {super("Botrental not found.");}
    public BotRentalNotFound(String message) {super(message);}
}
