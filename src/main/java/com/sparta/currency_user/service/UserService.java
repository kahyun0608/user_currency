package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.UserRequestDto;
import com.sparta.currency_user.dto.UserResponseDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto findById(Long id) {
        return new UserResponseDto(findUserById(id));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.");
        }
        User savedUser = userRepository.save(userRequestDto.toEntity());
        return new UserResponseDto(savedUser);
    }

    @Transactional
    public void deleteUserById(Long id) {
        this.findUserById(id);
        userRepository.deleteById(id);
    }

}
