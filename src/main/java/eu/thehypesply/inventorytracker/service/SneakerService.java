package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Sneaker;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SneakerService {

    List<Sneaker> getAllSneakers();
    Optional<Sneaker> getSneaker(String pid);
    String createSneaker(Sneaker sneaker);
    void deleteSneaker(String pid);
    void updateSneaker(String pid, Map<String, Object> fields);

}
