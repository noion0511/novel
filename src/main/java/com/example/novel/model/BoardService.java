package com.example.novel.model;

import com.example.exception.CustomException;
import com.example.exception.ErrorCode;
import com.example.novel.dto.BoardRequestDto;
import com.example.novel.dto.BoardResponseDto;
import com.example.novel.entity.Board;
import com.example.novel.entity.Post;
import com.example.novel.entity.User;
import com.example.novel.repository.BoardRepository;
import com.example.novel.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostService postService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(final HttpServletRequest request, final BoardRequestDto params) {
        Board entity = boardRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 게시글 리스트 조회
     */
    public List<BoardResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Board> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 게시글 조회
     */
    public Board findBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(final HttpServletRequest request, final Long id, final BoardRequestDto params) {
        User user = ((User) jwtTokenProvider.getAuthentication(jwtTokenProvider.resolveToken(request)).getPrincipal());
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), user);
        return id;
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public Boolean delete(final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        boardRepository.delete(entity);
        return entity.getId() != null;
    }
}
