package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.dto.PriceSoldDto;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Item;
import eu.thehypesply.inventorytracker.service.ImageService;
import eu.thehypesply.inventorytracker.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllClothing(Authentication auth) {
        return new ResponseEntity<>(itemService.getAllItems(auth), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getClothing(@PathVariable(value = "id") long id) {
        return new ResponseEntity<>(itemService.getItem(id), HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity<Object> createClothing(@ModelAttribute Item item, Authentication auth, @ModelAttribute MultipartFile photo) throws IOException {
        Image image = imageService.saveImage(photo);
        Item newItem = itemService.createItem(item, auth, image);
        imageService.saveImage(photo);
        return new ResponseEntity<>("Successfully created " + newItem.getItemName() , HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClothing(@PathVariable(value = "id") long id) {
        itemService.deleteClothing(id);
        return new ResponseEntity<>("Successfully deleted.", HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateClothing(@PathVariable(value = "id") long id, @RequestBody Map<String, Object> fields) {
        itemService.updateClothing(id, fields);
        return new ResponseEntity<>("Successfully updated.", HttpStatus.OK);
    }

    @GetMapping(value = "/invested")
    public ResponseEntity<Object> getTotalInvested() {
        return new ResponseEntity<>("You have $" + itemService.getTotalBought() + " invested in clothing.", HttpStatus.OK);
    }

    @GetMapping(value = "/sold")
    public ResponseEntity<Object> getTotalSold() {
        return new ResponseEntity<>("Your total in clothing sales is $" + itemService.getTotalSold(), HttpStatus.OK);
    }

    @GetMapping(value = "/balance")
    public ResponseEntity<Object> getBalance() {
        return new ResponseEntity<>("Your balance is $" + itemService.getBalance(), HttpStatus.OK);
    }

    @GetMapping(value = "/data")
    public ResponseEntity<Object> getItemData(Authentication auth) {
        return new ResponseEntity<>(itemService.getItemData(auth), HttpStatus.OK);
    }

    @GetMapping(value = "/sold-items")
    public ResponseEntity<Object> getSoldItems(Authentication auth){
        return new ResponseEntity<>(itemService.getSoldItems(auth), HttpStatus.OK);
    }

    @PostMapping(value = "/sell/{id}")
    public ResponseEntity<Object> sellItem(@PathVariable("id") long id, @RequestBody PriceSoldDto priceSold){
        itemService.sell(id, priceSold.getPriceSold());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
