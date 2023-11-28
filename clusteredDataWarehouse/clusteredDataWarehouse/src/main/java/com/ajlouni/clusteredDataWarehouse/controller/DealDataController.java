package com.ajlouni.clusteredDataWarehouse.controller;

import com.ajlouni.clusteredDataWarehouse.entity.FxDeal;
import com.ajlouni.clusteredDataWarehouse.serves.DealServes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scrumData")
public class DealDataController {
    @Autowired
    private DealServes dealServes;

    private final Logger logger = LoggerFactory.getLogger(DealDataController.class);

    @GetMapping("/findAll")
    public List<FxDeal> getAllFxDeal() {
        logger.info("Printing all FxDeal");
        return dealServes.getAllFx();
    }

    @PostMapping("/insert")
    public FxDeal insert(@RequestBody FxDeal fxDeal) {
        logger.info("Receiving the following request" + fxDeal);
        return dealServes.importDeal(fxDeal);
    }
}

