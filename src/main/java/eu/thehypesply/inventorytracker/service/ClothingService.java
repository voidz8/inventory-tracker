package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Clothing;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClothingService {

    List<Clothing> getAllClothing();
    Optional<Clothing> getClothing(String id);
    String createClothing(Clothing clothing);
    void deleteClothing(String id);
    void updateClothing(String id, Map<String, Object> fields);
}
