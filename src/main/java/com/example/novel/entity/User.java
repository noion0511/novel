package com.example.novel.entity;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql의 AUTO_INCREMENT를 그대로 사용
    private Long id;

    private String email;

    private String nickName;

    private String password;

    @LastModifiedDate
    private LocalDateTime createdAt;

    @Builder
    public User(String email, String nickName, String password) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public void update(String email, String nickName, String password) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }
}


