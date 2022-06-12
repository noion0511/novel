package com.example.novel.entity;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Board extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    private String title; // 제목

    private String content; // 내용

    private String writer; // 작성자

    private Long totalHits; // 전체 조회 수

    @LastModifiedDate
    private LocalDateTime createdDate = null; // 생성일

    @Builder
    public Board(String title, String content, String writer, Long totalHits) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.totalHits = totalHits;
    }

    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdDate = LocalDateTime.now();
    }
}
