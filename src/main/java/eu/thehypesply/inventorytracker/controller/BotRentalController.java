package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.model.BotRental;
import eu.thehypesply.inventorytracker.service.BotRentalService;
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
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping(value = "/botrental")
public class BotRentalController {

    @Autowired
    private BotRentalService botRentalService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllBotRentals(){
        return new ResponseEntity<>(botRentalService.getAllBotRentals(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getBotRental(@PathVariable(value = "id")String id){
        return new ResponseEntity<>(botRentalService.getBotRental(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createBotRental(@RequestBody BotRental botRental){
        String newBotRental = botRentalService.createBotRental(botRental);
        return new ResponseEntity<>("Successfully created a " + newBotRental+" botrental.", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteBotRental(@PathVariable(value = "id") String id){
        botRentalService.deleteBotRental(id);
        return new ResponseEntity<>("Successfully deleted botrental.", HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateBotRental(@PathVariable(value = "id") String id, @RequestBody Map<String, Object> fields) throws ParseException {
        botRentalService.updateBotRental(id, fields);
        return new ResponseEntity<>("Successfully updated botrental.", HttpStatus.OK);
    }

    @GetMapping(value = "/income")
    public ResponseEntity<Object> rentalIncome(){
        return new ResponseEntity<>("Your total rentalincome is $" + botRentalService.rentalIncome(), HttpStatus.OK);
    }
}
