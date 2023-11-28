package com.ajlouni.clusteredDataWarehouse.serves;

import com.ajlouni.clusteredDataWarehouse.entity.FxDeal;
import com.ajlouni.clusteredDataWarehouse.exception.DuplicateDealDataException;
import com.ajlouni.clusteredDataWarehouse.exception.InvalidDealDataException;
import com.ajlouni.clusteredDataWarehouse.repository.DealDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealServes {
    @Autowired
    private DealDataRepository dealDataRepository;
    public List<FxDeal> getAllFx(){
        return dealDataRepository.findAll();
    }
    public FxDeal importDeal(FxDeal dealData) {
        validateDealData(dealData);

        if (dealDataRepository.existsByDealUniqueId(dealData.getDealUniqueId())) {
            throw new DuplicateDealDataException("Deal with ID " + dealData.getDealUniqueId() + " already exists");
        }
        return dealDataRepository.save(dealData);
    }

    private void validateDealData(FxDeal dealData) {
        if (dealData.getDealUniqueId() == null || dealData.getDealUniqueId().isBlank()) {
            throw new InvalidDealDataException("Deal unique ID is required");
        }

        if (dealData.getFromCurrencyIsoCode() == null || dealData.getFromCurrencyIsoCode().isBlank()) {
            throw new InvalidDealDataException("From currency code is required");
        }

        if (dealData.getToCurrencyIsoCode() == null || dealData.getToCurrencyIsoCode().isBlank()) {
            throw new InvalidDealDataException("To currency code is required");
        }

        if (dealData.getDealAmount() == null || dealData.getDealAmount().signum() == 0) {
            throw new InvalidDealDataException("Deal amount must be a non-zero positive number");
        }

    }
}