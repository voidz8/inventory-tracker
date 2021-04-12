package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Sneaker;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SneakerService {

    List<Sneaker> getAllSneakers();
    Optional<Sneaker> getSneaker(String id);
    String createSneaker(Sneaker sneaker) throws InterruptedException, IOException;
    String createSneakerManual(Sneaker sneaker);
    void deleteSneaker(String id);
    void updateSneaker(String id, Map<String, Object> fields);
    long getTotalBought();
    long getTotalSold();
    long getBalance();
    void uploadInvoice(String id, Image image);

}
