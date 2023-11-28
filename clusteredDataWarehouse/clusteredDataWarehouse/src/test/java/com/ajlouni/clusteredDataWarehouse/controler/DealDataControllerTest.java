package com.ajlouni.clusteredDataWarehouse.controler;

import com.ajlouni.clusteredDataWarehouse.serves.DealServes;
import com.ajlouni.clusteredDataWarehouse.controller.DealDataController;
import com.ajlouni.clusteredDataWarehouse.entity.FxDeal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DealDataControllerTest {

    @Mock
    private DealServes dealServes;

    @InjectMocks
    private DealDataController dealDataController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFxDeal() {
        List<FxDeal> expectedFxDeals = Arrays.asList(
                new FxDeal(10L, "T1", "USD", "EUR", LocalDateTime.now(), new BigDecimal("1000.50")),
                new FxDeal(15L, "T2", "USD", "EUR", LocalDateTime.now(), new BigDecimal("1500.50"))
        );

        when(dealServes.getAllFx()).thenReturn(expectedFxDeals);
        List<FxDeal> actualFxDeals = dealDataController.getAllFxDeal();
        assertEquals(expectedFxDeals, actualFxDeals);
        verify(dealServes, times(1)).getAllFx();
    }

    @Test
    public void testInsertFxDeal() {
        FxDeal fxDealToInsert = new FxDeal(10L, "T1", "USD", "EUR", LocalDateTime.now(), new BigDecimal("1000.50"));
        when(dealServes.importDeal(fxDealToInsert)).thenReturn(fxDealToInsert);
        FxDeal insertedFxDeal = dealDataController.insert(fxDealToInsert);
        assertEquals(fxDealToInsert, insertedFxDeal);
        verify(dealServes, times(1)).importDeal(fxDealToInsert);
    }
}
