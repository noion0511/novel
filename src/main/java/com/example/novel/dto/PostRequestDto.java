package com.example.novel.dto;

import com.example.novel.entity.Board;
import com.example.novel.entity.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDto {

    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .hits(0L)
                .build();
    }
}
