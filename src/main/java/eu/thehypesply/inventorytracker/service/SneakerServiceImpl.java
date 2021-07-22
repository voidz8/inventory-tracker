package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataBoughtDto;
import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.dto.DataSoldDto;
import eu.thehypesply.inventorytracker.exception.SneakerNotFound;
import eu.thehypesply.inventorytracker.exception.UserNotFoundException;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Item;
import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.model.User;
import eu.thehypesply.inventorytracker.repository.SneakerRepository;
import eu.thehypesply.inventorytracker.repository.TotalRepository;
import eu.thehypesply.inventorytracker.repository.UserRepository;
import eu.thehypesply.inventorytracker.service.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SneakerServiceImpl implements SneakerService {

    @Autowired
    private SneakerRepository sneakerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TotalRepository totalRepository;


    public List<Sneaker> getAllSneakers(User user) {
        List<Sneaker> sneakers = sneakerRepository.findAllByUser(user);
        Collections.sort(sneakers);
        return sneakers;
    }

    public Optional<Sneaker> getSneaker(long id) {
        return sneakerRepository.findById(id);
    }


    @Override
    public Sneaker createSneakerManual(Sneaker sneaker, Authentication auth, Image image) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found")
        );
        sneaker.setDateBought(LocalDate.now());
        sneaker.setUser(user);
        sneaker.setImage(image);
        sneakerRepository.save(sneaker);
        return sneaker;
    }

    public void deleteSneaker(long id) {
        if (!sneakerRepository.existsById(id)) {
            throw new SneakerNotFound();
        }
        sneakerRepository.deleteById(id);
    }

    @Override
    public void updateSneaker(long id, Map<String, Object> fields) {
        if (!sneakerRepository.existsById(id)) {
            throw new SneakerNotFound();
        }
        Sneaker sneaker = sneakerRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field) {
                case "sneakerName":
                    sneaker.setSneakerName((String) fields.get(field));
                    break;
                case "size":
                    sneaker.setSneakerSize((Integer) fields.get(field));
                    break;
                case "priceBought":
                    sneaker.setPriceBought((Integer) fields.get(field));
                    break;
                case "salePrice":
                    sneaker.setSalePrice((Integer) fields.get(field));
                    break;
            }
        }
        sneakerRepository.save(sneaker);
    }

    @Override
    public long getTotalBought() {
        List<Sneaker> sneakers = sneakerRepository.findAll();
        long total = 0;
        for (Sneaker sneaker : sneakers) {
            long value = sneaker.getPriceBought();
            total = total + value;
        }
        return total;
    }

    @Override
    public long getTotalSold() {
        List<Sneaker> sneakers = sneakerRepository.findAll();
        long total = 0;
        for (Sneaker sneaker : sneakers) {
            long value = sneaker.getSalePrice();
            total = total + value;
        }
        return total;
    }

    @Override
    public long getBalance() {
        return getTotalSold() - getTotalBought();
    }



    @Override
    public void sold(long id, int priceSold) {
        Sneaker sneaker = sneakerRepository.findById(id).get();
        sneaker.setDateSold(LocalDate.now());
        sneaker.setSalePrice(priceSold);
        sneakerRepository.save(sneaker);
    }

    @Override
    public List<DataDto> getSneakerData(Authentication auth) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        List<Sneaker> sneakers = sneakerRepository.findAllByUser(user);
        List<DataDto> itemData = new ArrayList<>();
        List<DataDto> sortedData = new ArrayList<>();
        for (Sneaker sneaker : sneakers) {
            if (sneaker.getDateBought().isAfter(LocalDate.now().minusDays(30))) {
                DataDto sneakerDto = new DataDto(sneaker.getDateBought(), sneaker.getPriceBought());
                itemData.add(sneakerDto);
            }
            if (sneaker.getDateSold() != null && sneaker.getDateSold().isAfter(LocalDate.now().minusDays(30))) {
                DataDto dataSoldDto = new DataDto();
                dataSoldDto.setDate(sneaker.getDateSold());
                dataSoldDto.setPriceSold(sneaker.getSalePrice());
                itemData.add(dataSoldDto);
            }
        }
        for (int i = 0; i < 30; i++ ){
            DataDto dto = new DataDto(LocalDate.now().minusDays(i), 0, 0);
            sortedData.add(dto);
        }
        for (DataDto sorteditem : sortedData){
            for (DataDto item: itemData) {
                if (item.getDate().equals(sorteditem.getDate())){
                    sorteditem.setPriceSold(sorteditem.getPriceSold()+ item.getPriceSold());
                    sorteditem.setPriceBought(sorteditem.getPriceBought() + item.getPriceBought());
                }
            }
        }

        return sortedData;
    }

    @Override
    public List<Sneaker> getAllSoldSneakers(Authentication auth) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        List<Sneaker> sneakers = sneakerRepository.findAllByUser(user);
        List<Sneaker> soldSneakers = new ArrayList<>();
        for (Sneaker sneaker : sneakers){
            if (sneaker.getDateSold() != null){
                soldSneakers.add(sneaker);
            }
        }
        return soldSneakers;
    }

}
