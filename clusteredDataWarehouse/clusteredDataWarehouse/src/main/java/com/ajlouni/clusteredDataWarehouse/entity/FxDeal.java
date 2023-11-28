package com.ajlouni.clusteredDataWarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "fxDeal")
@Entity
public class FxDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dealUniqueId;
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    @PrePersist
    private void setDealTimestamp() {
        this.dealTimestamp = LocalDateTime.now();
    }
    private LocalDateTime dealTimestamp;
    private BigDecimal dealAmount;
}
