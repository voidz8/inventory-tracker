package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.BotRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotRentalRepository extends JpaRepository<BotRental, Long> {
}
