package com.example.novel.model;

import com.example.exception.CustomException;
import com.example.exception.ErrorCode;
import com.example.novel.dto.BoardRequestDto;
import com.example.novel.dto.BoardResponseDto;
import com.example.novel.dto.UserRequestDto;
import com.example.novel.dto.UserResponseDto;
import com.example.novel.entity.Board;
import com.example.novel.entity.User;
import com.example.novel.repository.BoardRepository;
import com.example.novel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 생성
     */
    @Transactional
    public Long save(final UserRequestDto params) {
        User entity = userRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 사용자 생성
     */
    @Transactional
    public Long logIn(String email, String passowrd) {
        User entity = userRepository.findByEmail(email).get();
        String password2 = entity.getPassword();
        if (!Objects.equals(passowrd, password2)) {
            return -1L;
        }
        return entity.getId();
    }

    /**
     * 사용자 리스트 조회
     */
    public List<UserResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<User> list = userRepository.findAll(sort);
        return list.stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 사용자 조회
     */
    public User findUser(Long boardId) {
        return userRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
    }

    /**
     * 사용자 수정
     */
    @Transactional
    public Long update(final Long id, final UserRequestDto params) {
        User entity = userRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getEmail(), params.getNickName(), params.getPassword());
        return id;
    }

    /**
     * 사용자 삭제
     */
    @Transactional
    public Boolean delete(final Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        userRepository.delete(entity);

        return entity.getId() != null;
    }
}

