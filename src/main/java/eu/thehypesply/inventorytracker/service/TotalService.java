package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Total;

import java.util.List;

public interface TotalService {

    long totalInvested();
    long totalProfit();
    long balance();
}
