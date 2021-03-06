package eu.thehypesply.inventorytracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import javassist.bytecode.ByteArray;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Blob;
import java.time.LocalDate;

@Entity
@Data
public class Sneaker implements Comparable<Sneaker> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pid;
    private String sneakerName;
    private long sneakerSize;
    private long priceBought;
    private Long priceSold;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateBought;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateSold;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Sneaker() {
    }

    public Sneaker(Sneaker sneaker) {
        this.pid = sneaker.getPid();
        this.sneakerName = sneaker.getSneakerName();
        this.sneakerSize = sneaker.getSneakerSize();
        this.priceBought = sneaker.getPriceBought();
    }

    @Override
    public int compareTo(Sneaker o) {
        return getDateBought().compareTo(o.getDateBought());
    }
}
