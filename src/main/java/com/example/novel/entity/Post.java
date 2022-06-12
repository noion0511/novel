package com.example.novel.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Post {
    @Id
    private Long pid;

    private String title; // 제목

    private String content; // 내용

    private Long hits; // 조회 수

    private LocalDateTime createdDate = LocalDateTime.now(); // 생성일

    @ManyToOne
    private Board board;

    @Builder
    public Post(String title, String content, Long hits, LocalDateTime createdDate) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.createdDate = createdDate;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }
}
