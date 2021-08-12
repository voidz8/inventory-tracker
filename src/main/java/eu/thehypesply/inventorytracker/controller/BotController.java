package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.dto.PriceSoldDto;
import eu.thehypesply.inventorytracker.model.Bot;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.service.BotService;
import eu.thehypesply.inventorytracker.service.ImageService;
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
@RequestMapping(value = "/bots")
public class BotController {

    @Autowired
    private BotService botService;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllBots(Authentication auth) {
        return new ResponseEntity<>(botService.getAllBots(auth), HttpStatus.OK);
    }

    @GetMapping(value = "/sold")
    public ResponseEntity<Object> getAllSoldBots(Authentication auth){
        return new ResponseEntity<>(botService.getAllSoldBots(auth), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getBot(@PathVariable(value = "id") long id) {
        return new ResponseEntity<>(botService.getBot(id), HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity<Object> createBot(@ModelAttribute Bot bot, Authentication auth, @ModelAttribute MultipartFile photo) throws IOException {
        Image image = imageService.saveImage(photo);
        String newBot = botService.createBot(bot, auth, image);
        imageService.saveImage(photo);
        return new ResponseEntity<>("Successfully added: " + newBot, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteBot(@PathVariable(value = "id") long id) {
        botService.deleteBot(id);
        return new ResponseEntity<>("Successfully deleted.", HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateBot(@PathVariable(value = "id") long id, @RequestBody Map<String, Object> fields) {
        botService.updateBot(id, fields);
        return new ResponseEntity<>("Succesfully updated", HttpStatus.OK);
    }

    @GetMapping(value = "/invested")
    public ResponseEntity<Object> getTotalInvested() {
        return new ResponseEntity<>("You have $" + botService.totalBotInvestment() + " invested in bots.", HttpStatus.OK);
    }

    @GetMapping(value = "/solda")
    public ResponseEntity<Object> getTotalSold() {
        return new ResponseEntity<>("Your total in sales is $" + botService.totalBotSales(), HttpStatus.OK);
    }

    @GetMapping(value = "/balance")
    public ResponseEntity<Object> getBalance() {
        return new ResponseEntity<>("Your balance is $" + botService.totalBalance(), HttpStatus.OK);
    }

    @GetMapping(value = "/data")
    public ResponseEntity<Object> getBotData(Authentication auth) {
        return new ResponseEntity<>(botService.getBotData(auth), HttpStatus.OK);
    }

    @PostMapping(value = "/sell/{id}")
    public ResponseEntity<Object> sellBot(@PathVariable("id") long id, @RequestBody PriceSoldDto priceSold){
        botService.sellBot(id, priceSold.getPriceSold());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
