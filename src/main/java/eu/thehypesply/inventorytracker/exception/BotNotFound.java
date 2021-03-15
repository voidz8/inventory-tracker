package eu.thehypesply.inventorytracker.exception;

public class BotNotFound extends RuntimeException{

    public BotNotFound() {super("Bot not found.");}

    public BotNotFound(String message) {super(message);}
}
