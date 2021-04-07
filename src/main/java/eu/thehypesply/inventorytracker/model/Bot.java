package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
public class Bot {

    @Id
    private String id;

    @Field
    private String botName;

    @Field
    private long priceBought;

    @Field
    private long priceSold;

    @Field
    private long rentalIncome;

    @DBRef(lazy = true)
    private BotRental botRental;

    public Bot() {
    }
}
