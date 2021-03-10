package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Total")
public class Total {

    @Id
    private String total;

    private long balance;

    private long value;

    public Total() {
    }
}
