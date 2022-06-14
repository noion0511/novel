package com.example.novel.dto;

import com.example.novel.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private Long totalHits; // 조회 수
    private LocalDateTime createdDate; // 생성일

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.totalHits = entity.getTotalHits();
        this.createdDate = entity.getCreatedDate();
    }

}
