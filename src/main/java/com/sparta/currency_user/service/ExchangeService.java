package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.EveryExchangeResponseDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.repository.ExchangeRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final UserRepository userRepository;

    public ExchangeResponseDto save (UserCurrency userCurrency) {
        UserCurrency savedUserCurrency= exchangeRepository.save(userCurrency);
        return ExchangeResponseDto.toDto(savedUserCurrency);
    }

    public List<ExchangeResponseDto> findExchangeRequest (Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        List<UserCurrency> exchangeRequest = exchangeRepository.findExchangeRequest(userId);
        return exchangeRequest.stream()
                .map(ExchangeResponseDto::toDto).collect(Collectors.toList());
    }

    public List<EveryExchangeResponseDto> findEveryExchangeRequest () {
        return exchangeRepository.findEveryExchangeRequest();
    }

    public ExchangeResponseDto cancelExchange (Long exchangeId) {
        UserCurrency exchangeFoundById = exchangeRepository.findById(exchangeId).orElseThrow(()-> new IllegalArgumentException("해당하는 환전 요청을 찾을 수 없습니다."));
        exchangeFoundById.setStatus("cancelled");
        return ExchangeResponseDto.toDto(exchangeFoundById);
    }


}
