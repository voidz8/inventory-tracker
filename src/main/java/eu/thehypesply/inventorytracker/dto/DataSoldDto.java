package eu.thehypesply.inventorytracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DataSoldDto {
    private LocalDate date;
    private long priceSold;

    public DataSoldDto(LocalDate date, long priceSold) {
        this.date = date;
        this.priceSold = priceSold;
    }
}
