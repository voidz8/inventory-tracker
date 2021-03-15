package eu.thehypesply.inventorytracker.exception;

public class ProxyNotFound extends RuntimeException{

    public ProxyNotFound() {super("Proxy not found."); }
    public ProxyNotFound(String message) {super(message); }
}
