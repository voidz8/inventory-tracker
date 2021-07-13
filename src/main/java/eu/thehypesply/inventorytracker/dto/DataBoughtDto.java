package eu.thehypesply.inventorytracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DataBoughtDto {
    private LocalDate date;
    private long priceBought;


    public DataBoughtDto(LocalDate date, long priceBought) {
        this.date = date;
        this.priceBought = priceBought;
    }
}
