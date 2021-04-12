package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.model.Clothing;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.service.ClothingService;
import eu.thehypesply.inventorytracker.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/clothing")
public class ClothingController {

    @Autowired
    private ClothingService clothingService;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllClothing(){
        return new ResponseEntity<>(clothingService.getAllClothing(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getClothing(@PathVariable(value = "id") String id){
        return new ResponseEntity<>(clothingService.getClothing(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createClothing(@RequestBody Clothing clothing){
        String newCloting = clothingService.createClothing(clothing);
        return new ResponseEntity<>("Successfully created " + newCloting , HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClothing(@PathVariable(value = "id") String id){
        clothingService.deleteClothing(id);
        return new ResponseEntity<>("Successfully deleted.", HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateClothing(@PathVariable(value = "id") String id, @RequestBody Map<String, Object> fields){
        clothingService.updateClothing(id,fields);
        return new ResponseEntity<>("Successfully updated.", HttpStatus.OK);
    }

    @GetMapping(value = "/invested")
    public ResponseEntity<Object> getTotalInvested(){
        return new ResponseEntity<>("You have $" + clothingService.getTotalBought() + " invested in clothing.", HttpStatus.OK);
    }

    @GetMapping(value = "/sold")
    public ResponseEntity<Object> getTotalSold(){
        return new ResponseEntity<>("Your total in clothing sales is $" + clothingService.getTotalSold(), HttpStatus.OK);
    }

    @GetMapping(value = "/balance")
    public ResponseEntity<Object> getBalance(){
        return new ResponseEntity<>("Your balance is $" + clothingService.getBalance(), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/invoice")
    public ResponseEntity<Object> addInvoice(@PathVariable(value = "id") String id, @RequestParam("file")MultipartFile file){
        Image image = imageService.storeImage(file);
        clothingService.uploadInvoice(id, image);
        return new ResponseEntity<>("Successfully uploaded invoice.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/invoice")
    public ResponseEntity<Object> deleteInvoice(@PathVariable(value = "id") String id){
        clothingService.deleteInvoice(id);
        return new ResponseEntity<>("Successfully deleted invoice.", HttpStatus.NO_CONTENT);
    }
}
