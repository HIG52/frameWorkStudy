package org.example.frameworkstudy.repository;

import org.example.frameworkstudy.entity.Boards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Boards, Integer> {

    @Modifying
    @Query("UPDATE Boards p SET p.viewCount = p.viewCount + :increment WHERE p.boardId = :boardId")
    void incrementViewCount(@Param("boardId") Integer boardId, @Param("increment") int increment);

}
