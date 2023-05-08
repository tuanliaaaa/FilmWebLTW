package film.api.repository;

import film.api.models.Chapter;
import film.api.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    @Query("SELECT C FROM Chapter C JOIN Film F on F.Id=C.Film.Id where F.Id=:filmID")
    List<Chapter> getChapterByFilmID(@Param("filmID") Long filmID);
}