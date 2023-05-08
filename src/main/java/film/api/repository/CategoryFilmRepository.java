package film.api.repository;

import film.api.models.CategoryFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryFilmRepository extends JpaRepository<CategoryFilm, Long> {
}
