package com.example.novel;

import com.example.novel.entity.User;
import com.example.novel.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class UserRepositoryTest extends NovelApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        // User 생성
        User user =
                User.builder()
                        .account("Test User 03")
                        .email("testuser03@google.com")
                        .phoneNumber("010-1234-5678")
                        .createdAt(LocalDateTime.now())
                        .createdBy("admin")
                        .build();

        // Create!
        User newUser = userRepository.save(user);
        log.info(newUser.toString());
    }

    @Test
    public void read() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(
                selectUser -> { // ifPresent: 값이 있다면, 가져옴
                    log.info(selectUser.toString());
                });
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(
                selectUser -> {
                    User newUser =
                            User.builder()
                                    .id(selectUser.getId())
                                    .account(selectUser.getAccount())
                                    .phoneNumber(selectUser.getPhoneNumber())
                                    .email("updatedEmail@google.com")
                                    .createdAt(selectUser.getCreatedAt())
                                    .createdBy(selectUser.getCreatedBy())
                                    .updatedAt(LocalDateTime.now())
                                    .updatedBy("TestUser02")
                                    .build();
                    userRepository.save(newUser);
                });
    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(1L);
        // User가 있어야 함
        Assertions.assertTrue(user.isPresent());

        // 있다면 삭제
        user.ifPresent(
                selectUser -> {
                    userRepository.deleteById(selectUser.getId());
                });

        // 지워졌는지 확인
        Optional<User> deletedUser = userRepository.findById(1L);
        Assertions.assertFalse(deletedUser.isPresent());
    }
}
