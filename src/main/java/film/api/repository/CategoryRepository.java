package film.api.repository;

import film.api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT a FROM Category a WHERE a.CategoryName LIKE %:key%")
    List<Category> findCategoryByName(@Param("key") String key);
}