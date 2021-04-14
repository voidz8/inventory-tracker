package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.exception.ClothingNotFound;
import eu.thehypesply.inventorytracker.model.Clothing;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.repository.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClothingServiceImpl implements ClothingService{

    @Autowired
    private ClothingRepository clothingRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public List<Clothing> getAllClothing() {
        return clothingRepository.findAll();
    }

    @Override
    public Optional<Clothing> getClothing(String id) {
        if (!clothingRepository.existsById(id)){throw new ClothingNotFound();}
        return clothingRepository.findById(id);
    }

    @Override
    public String createClothing(Clothing clothing) {
        Clothing newItem = clothingRepository.save(clothing);
        return newItem.getName();
    }

    @Override
    public void deleteClothing(String id) {
        clothingRepository.deleteById(id);
    }

    @Override
    public void updateClothing(String id, Map<String, Object> fields) {
        if (!clothingRepository.existsById(id)){throw new ClothingNotFound();}
        Clothing item = clothingRepository.findById(id).get();
        for (String field : fields.keySet()){
            switch (field){
                case "name":
                    item.setName((String) fields.get(field));
                    break;
                case "priceBought":
                    item.setPriceBought((Integer) fields.get(field));
                    break;
                case "priceSold":
                    item.setPriceSold((Integer) fields.get(field));
                    break;
            }
        }
        clothingRepository.save(item);
    }

    @Override
    public long getTotalBought() {
        List<Clothing> allClothing = clothingRepository.findAll();
        long total = 0;
        for (Clothing clothing : allClothing){
            long value = clothing.getPriceBought();
            total = total + value;
        }
        return total;
    }

    @Override
    public long getTotalSold() {
        List<Clothing> allClothing = clothingRepository.findAll();
        long total = 0;
        for (Clothing clothing : allClothing){
            long value = clothing.getPriceSold();
            total = total + value;
        }
        return total;
    }

    @Override
    public long getBalance() {
        return getTotalSold() - getTotalBought();
    }

    @Override
    public void uploadInvoice(String id, Image image) {
        if (!clothingRepository.existsById(id)){throw new ClothingNotFound();}
        Clothing clothing = clothingRepository.findById(id).get();
        clothing.setInvoice(image);
        clothingRepository.save(clothing);
    }

    @Override
    public void deleteInvoice(String id) {
        if (!clothingRepository.existsById(id)){throw new ClothingNotFound();}
        Clothing clothing = clothingRepository.findById(id).get();
        String imageId = clothing.getInvoice().getId();
        clothing.setInvoice(null);
        imageService.deleteImage(imageId);
        clothingRepository.save(clothing);
    }
}
