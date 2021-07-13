package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.exception.BotNotFound;
import eu.thehypesply.inventorytracker.exception.UserNotFoundException;
import eu.thehypesply.inventorytracker.model.Bot;
import eu.thehypesply.inventorytracker.model.User;
import eu.thehypesply.inventorytracker.repository.BotRepository;
import eu.thehypesply.inventorytracker.repository.UserRepository;
import eu.thehypesply.inventorytracker.service.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BotServiceImpl implements BotService {

    @Autowired
    private BotRepository botRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Bot> getAllBots() {
        return botRepository.findAll();
    }

    @Override
    public Optional<Bot> getBot(long id) {
        if (!botRepository.existsById(id)) {
            throw new BotNotFound();
        }
        return botRepository.findById(id);
    }

    @Override
    public String createBot(Bot bot) {
        Bot newBot = botRepository.save(bot);
        return newBot.getBotName();
    }

    @Override
    public void deleteBot(long id) {
        if (!botRepository.existsById(id)) {
            throw new BotNotFound();
        }
        botRepository.deleteById(id);
    }

    @Override
    public void updateBot(long id, Map<String, Object> fields) {
        if (!botRepository.existsById(id)) {
            throw new BotNotFound();
        }
        Bot bot = botRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field) {
                case "botName":
                    bot.setBotName((String) fields.get(field));
                    break;
                case "priceBought":
                    bot.setPriceBought((Integer) fields.get(field));
                    break;
                case "priceSold":
                    bot.setPriceSold((Long) fields.get(field));
                    break;
            }
        }
        botRepository.save(bot);
    }

    @Override
    public long totalBotInvestment() {
        List<Bot> bots = botRepository.findAll();
        long total = 0;
        for (Bot bot : bots) {
            long value = bot.getPriceBought();
            total = total + value;
        }
        return total;
    }

    @Override
    public long totalBotSales() {
        List<Bot> bots = botRepository.findAll();
        long total = 0;
        for (Bot bot : bots) {
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


    @Override
    public List<DataDto> getBotData(Authentication auth) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        List<Bot> bots = botRepository.findAllByUser(user);
        List<DataDto> itemData = new ArrayList<>();
        List<DataDto> sortedData = new ArrayList<>();
        for (Bot bot : bots) {
            if (bot.getDateBought().isAfter(LocalDate.now().minusDays(30))) {
                DataDto sneakerDto = new DataDto(bot.getDateBought(), bot.getPriceBought());
                itemData.add(sneakerDto);
            }
            if (bot.getDateSold() != null && bot.getDateSold().isAfter(LocalDate.now().minusDays(30))) {
                DataDto dataSoldDto = new DataDto();
                dataSoldDto.setDate(bot.getDateSold());
                dataSoldDto.setPriceSold(bot.getPriceSold());
                itemData.add(dataSoldDto);
            }
        }
        for (int i = 0; i < 30; i++) {
            DataDto dto = new DataDto(LocalDate.now().minusDays(i), 0, 0);
            sortedData.add(dto);
        }
        for (DataDto sorteditem : sortedData) {
            for (DataDto item : itemData) {
                if (item.getDate().equals(sorteditem.getDate())) {
                    sorteditem.setPriceSold(sorteditem.getPriceSold() + item.getPriceSold());
                    sorteditem.setPriceBought(sorteditem.getPriceBought() + item.getPriceBought());
                }
            }
        }

        return sortedData;
    }
}
