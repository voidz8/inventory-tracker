package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SneakerRepository extends JpaRepository<Sneaker, Long> {

    List<Sneaker> findAllByUser(User user);
}
