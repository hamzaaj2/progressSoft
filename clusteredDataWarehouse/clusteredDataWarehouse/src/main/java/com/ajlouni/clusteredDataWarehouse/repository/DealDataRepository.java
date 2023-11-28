package com.ajlouni.clusteredDataWarehouse.repository;

import com.ajlouni.clusteredDataWarehouse.entity.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealDataRepository extends JpaRepository<FxDeal, Integer> {
    boolean existsByDealUniqueId(String dealUniqueId);
}