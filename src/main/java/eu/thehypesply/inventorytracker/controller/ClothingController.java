package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.model.Clothing;
import eu.thehypesply.inventorytracker.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clothing")
public class ClothingController {

    @Autowired
    private ClothingService clothingService;

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
}
