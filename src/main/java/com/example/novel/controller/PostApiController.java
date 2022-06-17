package com.example.novel.controller;

import com.example.novel.dto.PostRequestDto;
import com.example.novel.dto.PostResponseDto;
import com.example.novel.entity.Board;
import com.example.novel.entity.Post;
import com.example.novel.model.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    /**
     * 게시글 생성
     */
    @PostMapping("/posts")
    public Long save(@RequestBody final PostRequestDto params) {
        return postService.save(params);
    }

    /**
     * 게시글 리스트 조회
     */
    @GetMapping("/posts")
    public List<PostResponseDto> findAll(Long boardId) {
        return postService.findAll(boardId);
    }

    /**
     * 게시글 조회
     */
    @GetMapping("/posts/{id}")
    public Post findPost(@PathVariable Long id) {
        postService.updateHits(id);
        return postService.findPost(id);
    }

    /**
     * 게시글 수정
     */
    @PatchMapping("/posts/{id}")
    public Long update(@PathVariable final Long id, @RequestBody final PostRequestDto params) {
        return postService.update(id, params);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/posts/{id}")
    public Boolean delete(@PathVariable final Long id) {
        return postService.delete(id);
    }
}
