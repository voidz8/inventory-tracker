package eu.thehypesply.inventorytracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DataDto {
    private LocalDate date;
    private long priceBought;
    private long priceSold;

    public DataDto() {
    }

    public DataDto(LocalDate date, long priceBought) {
        this.date = date;
        this.priceBought = priceBought;
    }

    public DataDto(LocalDate date, long priceBought, long priceSold) {
        this.date = date;
        this.priceBought = priceBought;
        this.priceSold = priceSold;
    }
}
