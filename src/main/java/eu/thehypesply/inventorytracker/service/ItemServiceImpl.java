package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataBoughtDto;
import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.dto.DataSoldDto;
import eu.thehypesply.inventorytracker.exception.ClothingNotFound;
import eu.thehypesply.inventorytracker.exception.UserNotFoundException;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Item;
import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.model.User;
import eu.thehypesply.inventorytracker.repository.ItemRepository;
import eu.thehypesply.inventorytracker.repository.UserRepository;
import eu.thehypesply.inventorytracker.service.user.UserDetailsImpl;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Item> getAllClothing() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getClothing(long id) {
        if (!itemRepository.existsById(id)) {
            throw new ClothingNotFound();
        }
        return itemRepository.findById(id);
    }

    @Override
    public String createClothing(Item item) {
        Item newItem = itemRepository.save(item);
        return newItem.getItemName();
    }

    @Override
    public void deleteClothing(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void updateClothing(long id, Map<String, Object> fields) {
        if (!itemRepository.existsById(id)) {
            throw new ClothingNotFound();
        }
        Item item = itemRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field) {
                case "name":
                    item.setItemName((String) fields.get(field));
                    break;
                case "priceBought":
                    item.setPriceBought((Integer) fields.get(field));
                    break;
                case "priceSold":
                    item.setPriceSold((Long) fields.get(field));
                    break;
            }
        }
        itemRepository.save(item);
    }

    @Override
    public long getTotalBought() {
        List<Item> allItem = itemRepository.findAll();
        long total = 0;
        for (Item item : allItem) {
            long value = item.getPriceBought();
            total = total + value;
        }
        return total;
    }

    @Override
    public long getTotalSold() {
        List<Item> allItem = itemRepository.findAll();
        long total = 0;
        for (Item item : allItem) {
            long value = item.getPriceSold();
            total = total + value;
        }
        return total;
    }

    @Override
    public long getBalance() {
        return getTotalSold() - getTotalBought();
    }

    @Override
    public void uploadInvoice(long id, Image image) {
        if (!itemRepository.existsById(id)) {
            throw new ClothingNotFound();
        }
        Item item = itemRepository.findById(id).get();
        item.setInvoice(image);
        itemRepository.save(item);
    }

    @Override
    public void deleteInvoice(long id) {
        if (!itemRepository.existsById(id)) {
            throw new ClothingNotFound();
        }
        Item item = itemRepository.findById(id).get();
        String imageId = item.getInvoice().getId();
        item.setInvoice(null);
        imageService.deleteImage(imageId);
        itemRepository.save(item);
    }

    @Override
    public List<DataDto> getItemData(Authentication auth) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        List<Item> items = itemRepository.findAllByUser(user);
        List<DataDto> itemData = new ArrayList<>();
        List<DataDto> sortedData = new ArrayList<>();
        for (Item item : items) {
            if (item.getDateBought().isAfter(LocalDate.now().minusDays(30))) {
                DataDto sneakerDto = new DataDto(item.getDateBought(), item.getPriceBought());
                itemData.add(sneakerDto);
            }
            if (item.getDateSold() != null && item.getDateSold().isAfter(LocalDate.now().minusDays(30))) {
                DataDto dataSoldDto = new DataDto();
                dataSoldDto.setDate(item.getDateSold());
                dataSoldDto.setPriceSold(item.getPriceSold());
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

}
