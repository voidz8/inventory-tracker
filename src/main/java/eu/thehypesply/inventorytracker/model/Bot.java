package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document
public class Bot {

    @Id
    private String id;

    @Field
    private String botName;

    @Field
    private String type;

    @Field
    private long priceBought;

    @Field
    private long priceSold;

    @Field
    private long rentalIncome;

    @Field
    private LocalDate dateBought;

    @Field
    private LocalDate dateSold;

    @DBRef(lazy = true)
    private BotRental botRental;

}
