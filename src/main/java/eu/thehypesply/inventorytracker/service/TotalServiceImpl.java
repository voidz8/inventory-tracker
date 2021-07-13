package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.repository.TotalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  TotalServiceImpl implements TotalService{

    @Autowired
    private TotalRepository totalRepository;

    @Override
    public long totalInvested() {
        return 0;
    }

    @Override
    public long totalProfit() {
        return 0;
    }

    @Override
    public long balance() {
        return 0;
    }
}
