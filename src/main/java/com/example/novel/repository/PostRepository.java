package com.example.novel.repository;

import com.example.novel.entity.Board;
import com.example.novel.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByBoard(Board board, Sort sort);

    @Modifying
    @Query("update Post p set p.hits = p.hits + 1 where p.pid = :pid")
    int updateHits(Long pid);
}
