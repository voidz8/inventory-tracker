package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.exception.BotNotFound;
import eu.thehypesply.inventorytracker.model.Bot;
import eu.thehypesply.inventorytracker.model.BotRental;
import eu.thehypesply.inventorytracker.repository.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BotServiceImpl implements BotService{

    @Autowired
    private BotRepository botRepository;

    @Override
    public List<Bot> getAllBots() {
        return botRepository.findAll();
    }

    @Override
    public Optional<Bot> getBot(String botName) {
        if (!botRepository.existsById(botName)){throw new BotNotFound();}
        return botRepository.findById(botName);
    }

    @Override
    public String createBot(Bot bot) {
        Bot newBot = botRepository.save(bot);
        return newBot.getBotName();
    }

    @Override
    public void deleteBot(String botName) {
        if (!botRepository.existsById(botName)){throw new BotNotFound();}
        botRepository.deleteById(botName);
    }

    @Override
    public void updateBot(String botName, Map<String, Object> fields) {
    if (!botRepository.existsById(botName)){throw new BotNotFound();}
    Bot bot = botRepository.findById(botName).get();
    for (String field : fields.keySet()){
        switch (field){
            case "priceBought":
                bot.setPriceBought((long) fields.get(field));
                break;
            case "priceSold":
                bot.setPriceSold((long) fields.get(field));
                break;
            case "value":
                bot.setRentalIncome((long) fields.get(field));
                break;
        }
    }
    botRepository.save(bot);
    }

    @Override
    public long addRentalIncome(String botname, BotRental botRental) {
        Bot bot = botRepository.findById(botname).get();
        bot.setRentalIncome(bot.getRentalIncome() + botRental.getPrice());
        return bot.getRentalIncome();
    }
}
