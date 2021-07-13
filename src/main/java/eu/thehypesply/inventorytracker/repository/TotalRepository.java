package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.Total;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalRepository extends JpaRepository<Total, Long> {
}
