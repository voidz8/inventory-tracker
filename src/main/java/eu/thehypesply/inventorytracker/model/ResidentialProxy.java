package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "ResidentialProxies")
public class ResidentialProxy extends Proxy {

    private String amount;

    public ResidentialProxy() {
    }
}
