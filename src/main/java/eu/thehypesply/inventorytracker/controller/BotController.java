package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.model.Bot;
import eu.thehypesply.inventorytracker.service.BotServiceImpl;
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

import java.util.Map;

@RestController
@RequestMapping(value = "/bot")
public class BotController {

    @Autowired
    private BotServiceImpl botService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllBots(){
        return new ResponseEntity<>(botService.getAllBots(), HttpStatus.OK);
    }

    @GetMapping(value = "/{botName}")
    public ResponseEntity<Object> getBot(@PathVariable(value = "botName") String botName){
        return new ResponseEntity<>(botService.getBot(botName), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createBot(@RequestBody Bot bot){
        String newBot = botService.createBot(bot);
        return new ResponseEntity<>("Successfully added: " + newBot, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{botName}")
    public ResponseEntity<Object> deleteBot(@PathVariable(value = "botName") String botName){
        botService.deleteBot(botName);
        return new ResponseEntity<>("Successfully deleted.",HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{botName}")
    public ResponseEntity<Object> updateBot(@PathVariable(value = "botName") String botName, @RequestBody Map<String, Object> fields){
        botService.updateBot(botName, fields);
        return new ResponseEntity<>("Succesfully updated: " + botName, HttpStatus.OK);
    }
}
