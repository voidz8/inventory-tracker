package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.ResiProxy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResiProxyRepository extends JpaRepository<ResiProxy, Long> {
}
