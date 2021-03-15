package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "ResidentialProxies")
public class ResiProxy extends Proxy {

    @Id
    @Field
    private String id;

    @Field
    private String amount;

    public ResiProxy() {
    }
}
