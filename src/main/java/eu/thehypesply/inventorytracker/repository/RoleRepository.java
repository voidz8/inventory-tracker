package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.ERole;
import eu.thehypesply.inventorytracker.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
