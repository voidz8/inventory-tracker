package eu.thehypesply.inventorytracker.service;
import eu.thehypesply.inventorytracker.exception.SneakerNotFound;
import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.repository.SneakerRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SneakerServiceImpl implements SneakerService{

//    private String URL = "https://www.google.com/imghp";
//    private ChromeDriver driver = new ChromeDriver();

    @Autowired
    private SneakerRepository sneakerRepository;


    public List<Sneaker> getAllSneakers(){return sneakerRepository.findAll();}

    public Optional<Sneaker> getSneaker(String pid){return sneakerRepository.findById(pid);}

    public String createSneaker(Sneaker sneaker){
        Sneaker newSneaker = sneakerRepository.save(sneaker);
        return newSneaker.getPid();
    }

    public void deleteSneaker(String pid) {
        if (!sneakerRepository.existsById(pid)) {throw new SneakerNotFound();}
        sneakerRepository.deleteById(pid);
    }

    @Override
    public void updateSneaker(String pid, Map<String, Object> fields) {
        if (!sneakerRepository.existsById(pid)) {throw new SneakerNotFound();}
        Sneaker sneaker = sneakerRepository.findById(pid).get();
        for (String field : fields.keySet()){
            switch (field){
                case "size":
                    sneaker.setSize((long) fields.get(field));
                    break;
                case "priceBought":
                    sneaker.setPriceBought((long) fields.get(field));
                    break;
                case "salePrice":
                    sneaker.setSalePrice((long) fields.get(field));
                    break;
            }
        }
        sneakerRepository.save(sneaker);
    }

    @Override
    public void addPhoto(String pid, MultipartFile file) throws IOException {
        if (!sneakerRepository.existsById(pid)){throw new SneakerNotFound();}
        Sneaker existingSneaker = sneakerRepository.findById(pid).get();
        existingSneaker.setPhoto(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        sneakerRepository.insert(existingSneaker);
        sneakerRepository.save(existingSneaker);
    }

}
