package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.exception.ClothingNotFound;
import eu.thehypesply.inventorytracker.exception.UserNotFoundException;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Item;
import eu.thehypesply.inventorytracker.model.User;
import eu.thehypesply.inventorytracker.repository.ItemRepository;
import eu.thehypesply.inventorytracker.repository.UserRepository;
import eu.thehypesply.inventorytracker.service.user.UserDetailsImpl;
import eu.thehypesply.inventorytracker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Item> getAllClothing(Authentication auth) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String userName = userDetails.getUsername();
        User user = userService.getUserByUsername(userName);
        return itemRepository.findAllByUser(user);
    }

    @Override
    public Optional<Item> getClothing(long id) {
        if (!itemRepository.existsById(id)) {
            throw new ClothingNotFound();
        }
        return itemRepository.findById(id);
    }

    @Override
    public Item createItem(Item item, Authentication auth, Image image) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userService.getUserByUsername(userDetails.getUsername());
        item.setDateBought(LocalDate.now());
        item.setUser(user);
        item.setImage(image);
        itemRepository.save(item);
        return item;
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
        for (int i = 0; i < 30; i++) {
            DataDto dto = new DataDto(LocalDate.now().minusDays(i), 0, 0);
            sortedData.add(dto);
        }
        for (DataDto sorteditem : sortedData) {
            for (DataDto item : itemData) {
                if (item.getDate().equals(sorteditem.getDate())) {
                    sorteditem.setPriceSold(sorteditem.getPriceSold() + item.getPriceSold());
                    sorteditem.setPriceBought(sorteditem.getPriceBought() + item.getPriceBought());
                }
            }
        }

        return sortedData;
    }

}
