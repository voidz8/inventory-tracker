package eu.thehypesply.inventorytracker.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Data
public class Sneaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String pid;
    private String sneakerName;
    private long sneakerSize;
    private long priceBought;
    private Integer salePrice;

    @OneToOne
    @JoinColumn(name = "image_id", updatable = false, insertable = false)
    private Image photo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", updatable = false, insertable = false)
    private Image invoice;

    private LocalDate dateBought;
    private LocalDate dateSold;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
