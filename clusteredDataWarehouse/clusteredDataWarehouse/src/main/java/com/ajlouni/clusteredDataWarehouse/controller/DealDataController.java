package com.ajlouni.clusteredDataWarehouse.controller;

import com.ajlouni.clusteredDataWarehouse.entity.FxDeal;
import com.ajlouni.clusteredDataWarehouse.serves.DealServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scrumData")
public class DealDataController {
    @Autowired
    private DealServes dealServes;

    @GetMapping("/findAll")
    public List<FxDeal> getAllFxDeal() {
    return dealServes.getAllFx();
    }

    @PostMapping("/insert")
    public FxDeal insert(@RequestBody FxDeal fxDeal) {
        return dealServes.importDeal(fxDeal);
    }
}

