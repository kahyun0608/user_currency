package com.sparta.currency_user.config;

import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class CurrencyCheck {

    @Autowired
    private CurrencyRepository currencyRepository;

    @PostConstruct
    public void init() {

        List<BigDecimal> everyExchangeRate = currencyRepository.findEveryExchangeRate();

        for(BigDecimal b : everyExchangeRate) {
            if (b.compareTo(BigDecimal.valueOf(0))<=0) {
                log.info("환율이 유효하지 않습니다.");
            }
        }
    }
}
