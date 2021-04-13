package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.exception.BotNotFound;
import eu.thehypesply.inventorytracker.model.Bot;
import eu.thehypesply.inventorytracker.repository.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BotServiceImpl implements BotService{

    @Autowired
    private BotRepository botRepository;

    @Override
    public List<Bot> getAllBots() {
        return botRepository.findAll();
    }

    @Override
    public Optional<Bot> getBot(String id) {
        if (!botRepository.existsById(id)){throw new BotNotFound();}
        return botRepository.findById(id);
    }

    @Override
    public String createBot(Bot bot) {
        Bot newBot = botRepository.save(bot);
        return newBot.getBotName();
    }

    @Override
    public void deleteBot(String id) {
        if (!botRepository.existsById(id)){throw new BotNotFound();}
        botRepository.deleteById(id);
    }

    @Override
    public void updateBot(String id, Map<String, Object> fields) {
    if (!botRepository.existsById(id)){throw new BotNotFound();}
    Bot bot = botRepository.findById(id).get();
    for (String field : fields.keySet()){
        switch (field){
            case "botName":
                bot.setBotName((String) fields.get(field));
                break;
            case "priceBought":
                bot.setPriceBought((Integer) fields.get(field));
                break;
            case "priceSold":
                bot.setPriceSold((Integer) fields.get(field));
                break;
        }
    }
    botRepository.save(bot);
    }

    @Override
    public long totalBotInvestment() {
        List<Bot> bots = botRepository.findAll();
        long total = 0;
        for (Bot bot : bots){
            long value = bot.getPriceBought();
            total = total + value;
        }
        return total;
    }

    @Override
    public long totalBotSales() {
        List<Bot> bots = botRepository.findAll();
        long total = 0;
        for (Bot bot : bots){
            long value = bot.getPriceSold();
            total = total + value;
        }
        return total;
    }

    @Override
    public long totalBalance() {
        return totalBotSales() - totalBotInvestment();
    }

//    @Override
//    public long addRentalIncome(String id, BotRental botRental) {
//        Bot bot = botRepository.findById(id).get();
//        bot.setRentalIncome(bot.getRentalIncome() + botRental.getPrice());
//        return bot.getRentalIncome();
//    }
}
