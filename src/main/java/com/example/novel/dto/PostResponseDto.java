package com.example.novel.dto;

import com.example.novel.entity.Board;
import com.example.novel.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private Long pid; // PK
    private String title; // 제목
    private String content; // 내용
    private Long hits; // 조회 수
    private LocalDateTime createdDate; // 생성일

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    private int sequence;

    public PostResponseDto(Post entity) {
        this.pid = entity.getPid();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.hits = entity.getHits();
        this.createdDate = entity.getCreatedDate();
    }
}