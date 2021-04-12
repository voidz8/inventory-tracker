package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.service.ImageService;
import eu.thehypesply.inventorytracker.service.SneakerService;
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

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/sneakers")
public class SneakerController {

    @Autowired
    private SneakerService sneakerService;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllSneakers(){
        return new ResponseEntity<>(sneakerService.getAllSneakers(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getSneaker(@PathVariable(value = "id") String id){
        return new ResponseEntity<>(sneakerService.getSneaker(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createSneaker(@RequestBody Sneaker sneaker) throws IOException, InterruptedException {
    String newSneaker = sneakerService.createSneaker(sneaker);
    return new ResponseEntity<>("Successfully created: " + newSneaker, HttpStatus.CREATED);
    }

    @PostMapping(value = "/manual")
    public ResponseEntity<Object> createSneakerManual(@RequestBody Sneaker sneaker){
        String newSneaker = sneakerService.createSneakerManual(sneaker);
        return new ResponseEntity<>("Successfully created: " + newSneaker, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteSneaker(@PathVariable(value = "id")String id){
        sneakerService.deleteSneaker(id);
        return new ResponseEntity<>("Successfully deleted.",HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateSneaker(@PathVariable(value = "id")String id, @RequestBody Map<String, Object> fields){
        sneakerService.updateSneaker(id,fields);
        return new ResponseEntity<>("Succesfully updated sneaker with pid: " + id+ ".", HttpStatus.OK);
    }

    @GetMapping(value = "/invested")
    public ResponseEntity<Object> getTotalInvested(){
        return new ResponseEntity<>("You have $"+ sneakerService.getTotalBought() + " invested in sneakers.", HttpStatus.OK);
    }

    @GetMapping(value = "/sold")
    public ResponseEntity<Object> getTotalSold(){
        return new ResponseEntity<>("Your total in sales is $" + sneakerService.getTotalSold(), HttpStatus.OK);
    }

    @GetMapping(value = "/balance")
    public ResponseEntity<Object> getBalance(){
       return new ResponseEntity<>("Your balance is $" + sneakerService.getBalance(), HttpStatus.OK);
    }



    @PostMapping(value = "/{id}/invoice")
    public ResponseEntity<Object> addInvoice(@PathVariable(value = "id")String id, @RequestParam("file") MultipartFile file){
        Image image = imageService.storeImage(file);
        sneakerService.uploadInvoice(id, image);
        return new ResponseEntity<>("Successfully uploaded invoice.", HttpStatus.OK);
    }

}
