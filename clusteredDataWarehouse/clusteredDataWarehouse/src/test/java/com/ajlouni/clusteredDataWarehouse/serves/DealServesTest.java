package com.ajlouni.clusteredDataWarehouse.serves;

import com.ajlouni.clusteredDataWarehouse.entity.FxDeal;
import com.ajlouni.clusteredDataWarehouse.exception.DealImportException;
import com.ajlouni.clusteredDataWarehouse.exception.InvalidDealDataException;
import com.ajlouni.clusteredDataWarehouse.repository.DealDataRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class DealServesTest {

    @Mock
    private DealDataRepository dealDataRepository;

    @InjectMocks
    private DealServes dealServes;

    @Test
    void getAllFx() {
        List<FxDeal> mockDeals = new ArrayList<>();
        Mockito.when(dealDataRepository.findAll()).thenReturn(mockDeals);
        List<FxDeal> result = dealServes.getAllFx();
        assertTrue(result.isEmpty());
    }

    @Test
    void importDeal_ValidDeal() {
        FxDeal mockDeal = new FxDeal();
        Mockito.when(dealDataRepository.existsByDealUniqueId(any())).thenReturn(false);
        Mockito.when(dealDataRepository.save(any())).thenReturn(mockDeal);
        FxDeal validDeal = new FxDeal();
        validDeal.setDealUniqueId("123");
        validDeal.setFromCurrencyIsoCode("USD");
        validDeal.setToCurrencyIsoCode("EUR");
        validDeal.setDealAmount(new BigDecimal("100.00"));
        FxDeal result = dealServes.importDeal(validDeal);
        assertTrue(result == mockDeal);
    }

    @Test
    void importDeal_DuplicateDeal() {
        Mockito.when(dealDataRepository.existsByDealUniqueId(any())).thenReturn(true);
        FxDeal duplicateDeal = new FxDeal();
        duplicateDeal.setDealUniqueId("123");
        assertThrows(DealImportException.class, () -> dealServes.importDeal(duplicateDeal));
    }

    @Test
    void importDeal_InvalidDeal() {
        FxDeal invalidDeal = new FxDeal();
        assertThrows(DealImportException.class, () -> dealServes.importDeal(invalidDeal));
    }
}
