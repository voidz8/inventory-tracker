package eu.thehypesply.inventorytracker.service;
import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.repository.SneakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Service
public class SneakerService {


    @Autowired
    private SneakerRepository sneakerRepository;


    public List<Sneaker> getAllSneakers(){return sneakerRepository.findAll();}

    public Optional<Sneaker> getSneaker(String pid){return sneakerRepository.findById(pid);}

    public String createSneaker(Sneaker sneaker){
        Sneaker newSneaker = sneakerRepository.save(sneaker);
        return newSneaker.getPid();
    }

    public void deleteSneaker(String pid){}

}
