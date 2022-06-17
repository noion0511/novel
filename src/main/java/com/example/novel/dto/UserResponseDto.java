package com.example.novel.dto;

import com.example.novel.entity.Board;
import com.example.novel.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private Long id; // PK
    private String email;
    private String nickName;
    private String password;
    private LocalDateTime createdAt; // 생성일

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickName = entity.getNickName();
        this.password = entity.getPassword();
        this.createdAt = entity.getCreatedAt();
    }
}
