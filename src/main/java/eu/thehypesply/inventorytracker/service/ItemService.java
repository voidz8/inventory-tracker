package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Item;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllClothing(Authentication auth);

    Optional<Item> getClothing(long id);

    Item createItem(Item item , Authentication auth, Image image) throws IOException;

    void deleteClothing(long id);

    void updateClothing(long id, Map<String, Object> fields);

    long getTotalBought();

    long getTotalSold();

    long getBalance();


    List<DataDto> getItemData(Authentication auth);
}
