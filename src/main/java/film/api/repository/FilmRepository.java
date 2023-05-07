package film.api.repository;

import film.api.models.Film;
import film.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f where f.FilmName LIKE %:filmName%")
    List<Film> findUsersByFilmNameContain(@Param("filmName") String filmName);
}