package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.adapters.ItemSaleRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemSaleService {

    private final ItemSaleRepositoryAdapter itemSaleRepositoryAdapter;

    @Autowired
    public ItemSaleService(ItemSaleRepositoryAdapter itemSaleRepositoryAdapter) {
        this.itemSaleRepositoryAdapter = itemSaleRepositoryAdapter;
    }
}
