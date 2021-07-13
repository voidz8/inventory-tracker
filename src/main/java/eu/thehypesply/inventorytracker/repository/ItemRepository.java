package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.Item;
import eu.thehypesply.inventorytracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUser(User user);


}
