package film.api.repository;

import film.api.DTO.ChapterHotDTO;
import film.api.models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query(nativeQuery = true, value = "SELECT history.chapter_id, COUNT(history.id) AS count, AVG(history.rate) AS avg, chapter.chapter_image, chapter.chapter_name " +
            "FROM history JOIN chapter " +
            "WHERE (history.history_view >= :fromDay AND history.history_view < :toDay) " +
            "GROUP BY history.Chapter_id, chapter.chapter_image, chapter.chapter_name ORDER BY count DESC")
    List<Object[]> getChaptersHotCount(@Param("fromDay") LocalDateTime fromDay, @Param("toDay") LocalDateTime toDay);

}