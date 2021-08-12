package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SneakerService {

    List<Sneaker> getAllSneakers(User user);

    Optional<Sneaker> getSneaker(long id);

    Sneaker createSneakerManual(Sneaker sneaker, Authentication auth, Image image) throws IOException;

    void deleteSneaker(long id);

    void updateSneaker(long id, Map<String, Object> fields);

    long getTotalBought();

    long getTotalSold();

    long getBalance();

    void sold(long id, Long priceSold);

    List<Sneaker> getAllSoldSneakers(Authentication auth);

    List<DataDto> getSneakerData(Authentication auth);

}
