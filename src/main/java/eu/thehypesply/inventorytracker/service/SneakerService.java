package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SneakerService {

    List<Sneaker> getAllSneakers(User user);

    Optional<Sneaker> getSneaker(long id);

    String createSneakerManual(Sneaker sneaker, Authentication auth, Image photo, Image invoice);

    void deleteSneaker(long id);

    void updateSneaker(long id, Map<String, Object> fields);

    long getTotalBought();

    long getTotalSold();

    long getBalance();

    void uploadInvoice(long id, Image image);

    void deleteInvoice(long id);

    //    void uploadPicture(String id, Image image);
//    void deletePicture(String id);
    void sold(long id, int priceSold);

    List<DataDto> getSneakerData(Authentication auth);


}
