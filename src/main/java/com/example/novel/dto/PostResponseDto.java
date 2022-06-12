package com.example.novel.dto;

import com.example.novel.entity.Board;
import com.example.novel.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private Long hits; // 조회 수
    private LocalDateTime createdDate; // 생성일

    public PostResponseDto(Post entity) {
        this.id = entity.getPid();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.hits = entity.getHits();
        this.createdDate = entity.getCreatedDate();
    }

}