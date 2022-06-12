package com.example.novel.controller;

import com.example.exception.CustomException;
import com.example.exception.ErrorCode;
import com.example.novel.dto.BoardRequestDto;
import com.example.novel.dto.BoardResponseDto;
import com.example.novel.entity.Board;
import com.example.novel.model.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    /**
     * 게시글 생성
     */
    @PostMapping("/boards")
    public Long save(@RequestBody final BoardRequestDto params) {
        return boardService.save(params);
    }

    /**
     * 게시글 리스트 조회
     */
    @GetMapping("/boards")
    public List<BoardResponseDto> findAll() {
        return boardService.findAll();
    }

    /**
     * 게시글 리스트 조회
     */
    @GetMapping("/boards/{id}")
    public Board findBoard(@PathVariable Long id) {
        return boardService.findBoard(id);
    }

    /**
     * 게시글 수정
     */
    @PatchMapping("/boards/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final BoardRequestDto params) {
        return boardService.update(id, params);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/boards/{id}")
    public Boolean delete(@PathVariable final Long id) {
        return boardService.delete(id);
    }
}
