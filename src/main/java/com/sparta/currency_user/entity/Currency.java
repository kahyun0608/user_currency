package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "currency")
@Getter
public class Currency extends BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyName;
    private BigDecimal exchangeRate;
    private String symbol;

    @OneToMany(mappedBy = "currency")
    private List<UserCurrency> userCurrencyList = new ArrayList<>();

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    public Currency() {}

    public static BigDecimal modifyExchangeRate (String currencyName, BigDecimal exchangeRate) {
        return switch (currencyName) {
            case "JPY" -> exchangeRate.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
            case "SEK", "CNY", "HKD" -> exchangeRate.divide(BigDecimal.valueOf(10), 2, RoundingMode.HALF_EVEN);
            default -> exchangeRate;
        };
    }

}
