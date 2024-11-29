package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query ("select c.exchangeRate from Currency c")
    public List<BigDecimal> findEveryExchangeRate ();

}
