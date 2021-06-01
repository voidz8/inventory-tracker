package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
