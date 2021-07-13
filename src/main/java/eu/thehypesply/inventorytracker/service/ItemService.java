package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Item;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllClothing();

    Optional<Item> getClothing(long id);

    String createClothing(Item item);

    void deleteClothing(long id);

    void updateClothing(long id, Map<String, Object> fields);

    long getTotalBought();

    long getTotalSold();

    long getBalance();

    void uploadInvoice(long id, Image image);

    void deleteInvoice(long id);

    List<DataDto> getItemData(Authentication auth);
}
