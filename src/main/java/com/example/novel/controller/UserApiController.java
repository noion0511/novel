package com.example.novel.controller;

import com.example.novel.dto.UserRequestDto;
import com.example.novel.dto.UserResponseDto;
import com.example.novel.entity.User;
import com.example.novel.model.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    /**
     * 사용자 생성
     */
    @PostMapping("/user")
    public Long save(@RequestBody final UserRequestDto params) {
        return userService.save(params);
    }

    /**
     * 사용자 리스트 조회
     */
    @GetMapping("/user")
    public List<UserResponseDto> findAll() {
        return userService.findAll();
    }

    /**
     * 사용자 리스트 조회 1개
     */
    @GetMapping("/user/{id}")
    public User findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    /**
     * 로그인?
     */
    @GetMapping("/logIn")
    public Long logIn(@RequestParam String email, @RequestParam String password) {
        return userService.logIn(email, password);
    }

    /**
     * 사용자 수정
     */
    @PatchMapping("/user/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final UserRequestDto params) {
        return userService.update(id, params);
    }

    /**
     * 사용자 삭제
     */
    @DeleteMapping("/user/{id}")
    public Boolean delete(@PathVariable final Long id) {
        return userService.delete(id);
    }
}
