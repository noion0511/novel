package com.example.novel.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String title; // 제목

    private String content; // 내용

    @Column(columnDefinition = "integer default 0", nullable = false)
    private Long hits; // 조회 수

    @ManyToOne()
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Post(String title, String content, Board board, Long hits) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.board = board;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
