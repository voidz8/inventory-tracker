package eu.thehypesply.inventorytracker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@Entity
public class Total {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long totalInvested = 0;
    private long totalProfit = 0;
    private long balance = 0;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Total() {
    }
}
