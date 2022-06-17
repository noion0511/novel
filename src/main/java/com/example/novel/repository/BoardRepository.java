package com.example.novel.repository;


import com.example.novel.entity.Board;
import com.example.novel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Modifying
    @Query("update Board b set b.totalHits = b.totalHits + 1 where b.id = (select a.board.id from Post a where a.pid = :pid)")
    int updateHits(Long pid);

    List<Board> findAllByWriter(User writer);
}
