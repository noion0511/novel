package com.example.novel.dto;

import com.example.novel.entity.Board;
import com.example.novel.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDto {

    private String email;
    private String nickName;
    private String password;

    public User toEntity() {
        return User.builder()
                .email(email)
                .nickName(nickName)
                .password(password)
                .build();
    }
}
