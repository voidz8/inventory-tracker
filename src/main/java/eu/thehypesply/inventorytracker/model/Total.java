package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Total")
public class Total {

    @Id
    private String id;

    @Field
    private long totalInvested;

    @Field
    private long totalProfit;

    @Field
    private long balance;


    public Total() {
    }
}
