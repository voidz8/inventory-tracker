package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "ResidentialProxies")
public class ResiProxy extends Proxy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long amount;

    public ResiProxy() {
    }
}
