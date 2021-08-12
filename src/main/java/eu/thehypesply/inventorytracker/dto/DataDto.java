package eu.thehypesply.inventorytracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DataDto {
    private LocalDate date;
    private long buy;
    private long sell;

    public DataDto() {
    }

    public DataDto(LocalDate date, long buy) {
        this.date = date;
        this.buy = buy;
    }

    public DataDto(LocalDate date, long buy, long sell) {
        this.date = date;
        this.buy = buy;
        this.sell = sell;
    }
}
