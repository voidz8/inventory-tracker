package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.exception.BotRentalNotFound;
import eu.thehypesply.inventorytracker.model.Bot;
import eu.thehypesply.inventorytracker.model.BotRental;
import eu.thehypesply.inventorytracker.repository.BotRentalRepository;
import eu.thehypesply.inventorytracker.repository.TotalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BotRentalServiceImpl implements BotRentalService{

    @Autowired
    private BotRentalRepository botRentalRepository;

    @Autowired
    private TotalRepository totalRepository;

//    @Autowired
//    private BotRepository botRepository;

    @Override
    public List<BotRental> getAllBotRentals() {
        return botRentalRepository.findAll();
    }

    @Override
    public Optional<BotRental> getBotRental(String id) {
        if (!botRentalRepository.existsById(id)){throw new BotRentalNotFound();}
        return botRentalRepository.findById(id);
    }

    @Override
    public String createBotRental(BotRental botRental) {
        BotRental newBotRental = botRentalRepository.save(botRental);

        return newBotRental.getBot().getBotName();
    }

    @Override
    public void deleteBotRental(String id) {

        botRentalRepository.deleteById(id);
    }

    @Override
    public void updateBotRental(String id, Map<String, Object> fields) throws ParseException {
    if (!botRentalRepository.existsById(id)){throw new BotRentalNotFound();}
    BotRental rental = botRentalRepository.findById(id).get();
    for (String field : fields.keySet()){
        switch (field){
            case "rentalDate":
                Date newRentalDate = new SimpleDateFormat("dd-MM-yyy").parse(fields.get(field).toString());
                newRentalDate.setTime(newRentalDate.getTime()+3600000);
                rental.setRentalDate(newRentalDate);
                break;
            case "rentalExpiry":
                Date newExpiryDate = new SimpleDateFormat("dd-MM-yyy").parse(fields.get(field).toString());
                newExpiryDate.setTime(newExpiryDate.getTime()+3600000);
                rental.setRentalExpiry(newExpiryDate);
                break;
            case "price":
                rental.setPrice((Integer) fields.get(field));
                break;
            case "bot":
                rental.setBot((Bot) fields.get(field));
                break;
        }
    }
    botRentalRepository.save(rental);
    }

    @Override
    public long rentalIncome() {
        List<BotRental> rentals = botRentalRepository.findAll();
        long total = 0;
        for (BotRental rental : rentals){
            long value = rental.getPrice();
            total = total + value;
        }
        return total;
    }
}
