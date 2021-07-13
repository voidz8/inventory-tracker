package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.DCProxy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DCProxyRepository extends JpaRepository<DCProxy, Long> {
}
