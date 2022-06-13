package com.example.novel.model;

import com.example.exception.CustomException;
import com.example.exception.ErrorCode;
import com.example.novel.dto.PostRequestDto;
import com.example.novel.dto.PostResponseDto;
import com.example.novel.entity.Board;
import com.example.novel.entity.Post;
import com.example.novel.repository.BoardRepository;
import com.example.novel.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(final PostRequestDto params) {

        Post entity = postRepository.save(params.toEntity(boardRepository.findById(params.getBoardId()).get()));
        return entity.getPid();
    }

    /**
     * 게시글 리스트 조회
     */

    public List<PostResponseDto> findAll(Long boardId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "pid");
        Board board = boardRepository.findById(boardId).get();
        List<Post> list =  postRepository.findAllByBoard(board, sort);
        return list.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 게시글 조회
     */
    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(final Long id, final PostRequestDto params) {

        Post entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent());
        return id;
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public Boolean delete(final Long id) {
        Post entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        postRepository.delete(entity);

        return entity.getPid() != null;
    }
}
