package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Item;
import eu.thehypesply.inventorytracker.model.Image;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClothingService {

    List<Item> getAllClothing();
    Optional<Item> getClothing(String id);
    String createClothing(Item item);
    void deleteClothing(String id);
    void updateClothing(String id, Map<String, Object> fields);
    long getTotalBought();
    long getTotalSold();
    long getBalance();
    void uploadInvoice(String id, Image image);
    void deleteInvoice(String id);
}
