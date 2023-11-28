package com.ajlouni.clusteredDataWarehouse.serves;

import com.ajlouni.clusteredDataWarehouse.entity.FxDeal;
import com.ajlouni.clusteredDataWarehouse.exception.DealImportException;
import com.ajlouni.clusteredDataWarehouse.exception.DuplicateDealDataException;
import com.ajlouni.clusteredDataWarehouse.exception.InvalidDealDataException;
import com.ajlouni.clusteredDataWarehouse.repository.DealDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Currency;
import java.util.List;

@Service
public class DealServes {
    @Autowired
    private DealDataRepository dealDataRepository;
    private final Logger logger = LoggerFactory.getLogger(DealServes.class);

    public List<FxDeal> getAllFx() {
        return dealDataRepository.findAll();
    }

    public FxDeal importDeal(FxDeal dealData) {
        try {
            logger.info("Start validation request");
            validateDealData(dealData);
            logger.info("Data required existence validation succeeded");
            if (dealDataRepository.existsByDealUniqueId(dealData.getDealUniqueId())) {
                throw new DuplicateDealDataException("Deal with ID " + dealData.getDealUniqueId() + " already exists");
            }
            logger.info("Data duplication validation succeeded");
            validateIsoCode(dealData.getFromCurrencyIsoCode());
            validateIsoCode(dealData.getToCurrencyIsoCode());
            logger.info("Data ISO code validation succeeded");
            logger.info("Data validated successfully");
            return dealDataRepository.save(dealData);
        } catch (Exception e) {
            logger.error("Error during deal import", e);
            throw new DealImportException("Error during deal import", e);
        }
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
    private void validateIsoCode(String code) {
        boolean flag = false;
        for(Currency currency : Currency.getAvailableCurrencies()) {
            if(currency.getCurrencyCode().equals(code)) {
                flag = true;
                break;
            }
        }
        if(!flag)
            throw new InvalidDealDataException("currency ISO code is invalid");
    }
}