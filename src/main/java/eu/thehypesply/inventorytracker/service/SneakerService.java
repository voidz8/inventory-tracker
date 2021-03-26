package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Sneaker;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SneakerService {

    List<Sneaker> getAllSneakers();
    Optional<Sneaker> getSneaker(String pid);
    String createSneaker(Sneaker sneaker);
    void deleteSneaker(String pid);
    void updateSneaker(String pid, Map<String, Object> fields);
    void addPhoto(String pid, MultipartFile file) throws IOException;

}
