package com.example.novel.dto;

import com.example.novel.entity.Board;
import com.example.novel.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private String title; // 제목
    private String content; // 내용

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .totalHits(0L)
                .build();
    }
}
