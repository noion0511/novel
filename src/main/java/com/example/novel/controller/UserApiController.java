package com.example.novel.controller;

import com.example.novel.entity.Board;
import com.example.novel.entity.Token;
import com.example.novel.entity.User;
import com.example.novel.repository.BoardRepository;
import com.example.novel.repository.UserRepository;
import com.example.novel.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final BoardRepository boardRepository;

    // 회원가입
    @PostMapping("/user")
    public User create(@RequestBody Map<String, String> newUser) {
        return userRepository.save(
                User.builder().
                        username(newUser.get("username")).
                        password(passwordEncoder.encode(newUser.get("password"))).
                        roles(Collections.singletonList("ROLE_USER"))
                        .build()
        );
    }

    // 로그인
    @PostMapping("/login")
    public Token login(@RequestBody Map<String, String> loginData) {
        User user = userRepository.findByUsername(loginData.get("username")).
                orElseThrow( () -> new IllegalArgumentException("Unvalid ID"));
        if (!passwordEncoder.matches(loginData.get("password"), user.getPassword())) {
            throw new IllegalArgumentException("Wrong PW");
        }
        return Token.builder().token(jwtTokenProvider.createToken(user.getUsername(),user.getRoles())).build();
    }

    //아이디 중복검사
    @GetMapping("/user/{username}")
    public boolean checkUsableName(@PathVariable(value = "username") String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    //회원 탈퇴
    @DeleteMapping("/user/{username}")
    public boolean deleteUser(Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        List<Board> boardList = boardRepository.findAllByWriter(user.get());
        for (Board board:boardList) {

            boardRepository.delete(board);
        }
        userRepository.delete(user.get());
        return userRepository.findByUsername(user.get().getUsername()).isEmpty();
    }


}
