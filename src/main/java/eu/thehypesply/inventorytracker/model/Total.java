package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Total")
public class Total {

    @Id
    private String id;

    @Field
    private long totalInvested = 0;

    @Field
    private long totalProfit = 0;

    @Field
    private long balance = 0;


    public Total() {
    }
}
