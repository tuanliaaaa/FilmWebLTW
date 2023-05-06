package film.api.repository;

import film.api.models.ActorChapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorChapterRepository extends JpaRepository<ActorChapter, Long> {
}