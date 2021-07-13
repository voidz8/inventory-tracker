package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
