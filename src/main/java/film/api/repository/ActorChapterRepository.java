package film.api.repository;

import film.api.models.ActorChapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorChapterRepository extends JpaRepository<ActorChapter, Long> {
    @Query("SELECT ac.Chapter FROM ActorChapter ac WHERE ac.Actor.id = :actorId")
    List<Chapter> findChaptersByActorId(@Param("actorId") Long actorId);




}