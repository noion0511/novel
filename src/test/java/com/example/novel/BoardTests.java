package com.example.novel;

import com.example.novel.entity.Board;
import com.example.novel.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class BoardTests {

    @Autowired
    BoardRepository boardRepository;

    // 1. 게시글 파라미터 생성
    Board params = Board.builder()
            .title("1번 게시글 제목")
            .content("1번 게시글 내용")
            .writer("1번 작성자")
            .totalHits(0L)
            .build();

    @Test
    void save() {
        // 2. 게시글 저장
        boardRepository.save(params);

        // 3. 1번 게시글 정보 조회
        Board entity = boardRepository.findById(params.getId()).get();

        assertThat(params.getId(), greaterThan(0L));
        assertThat(entity.getTitle(), is("1번 게시글 제목"));
        assertThat(entity.getContent(), is("1번 게시글 내용"));
        assertThat(entity.getWriter(), is("1번 작성자"));
    }

    @Test
    void findAll() {

        // 1. 전체 게시글 수 조회
        long boardsCount = boardRepository.count();

        // 2. 전체 게시글 리스트 조회
        List<Board> boards = boardRepository.findAll();
    }

    @Test
    void delete(){
        boardRepository.save(params);
        // 1. 게시글 조회
        Board entity = boardRepository.findById(params.getId()).get();

        // 2. 게시글 삭제
        boardRepository.delete(entity);
    }

}
