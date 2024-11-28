package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.EveryExchangeResponseDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<UserCurrency, Long> {

    @Query("select uc from UserCurrency uc join fetch uc.user u where u.id = :userId")
    List<UserCurrency> findExchangeRequest(@Param("userId") Long userId);

    //uc.user.id대신 join fetch를 해보려고 했는데 실패함...
    @Query("select new com.sparta.currency_user.dto.EveryExchangeResponseDto(uc.user.id, count(uc), sum(uc.amountInKrw)) " +
            "from UserCurrency uc group by uc.user")
    List<EveryExchangeResponseDto> findEveryExchangeRequest();
}
