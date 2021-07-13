package eu.thehypesply.inventorytracker.controller;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Object> getAllClothing() {
        return new ResponseEntity<>(itemService.getAllClothing(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getClothing(@PathVariable(value = "id") long id) {
        return new ResponseEntity<>(itemService.getClothing(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createClothing(@RequestBody Item item) {
        String newCloting = itemService.createClothing(item);
        return new ResponseEntity<>("Successfully created " + newCloting, HttpStatus.CREATED);
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

    @PostMapping(value = "/{id}/invoice")
    public ResponseEntity<Object> addInvoice(@PathVariable(value = "id") long id, @RequestParam("file") MultipartFile file) {
        Image image = imageService.storeImage(file);
        itemService.uploadInvoice(id, image);
        return new ResponseEntity<>("Successfully uploaded invoice.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/invoice")
    public ResponseEntity<Object> deleteInvoice(@PathVariable(value = "id") long id) {
        itemService.deleteInvoice(id);
        return new ResponseEntity<>("Successfully deleted invoice.", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/data")
    public ResponseEntity<Object> getItemData(Authentication auth) {
        return new ResponseEntity<>(itemService.getItemData(auth), HttpStatus.OK);
    }
}
