package film.api.controller;
import film.api.models.*;
import film.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/category", produces = "application/json")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("list")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category category = categoryRepository.findById(id).get();

        category.setCategoryName(categoryDetails.getCategoryName());

        Category updatedCategory = categoryRepository.save(category);
        return new ResponseEntity<>("Cập nhật thành công" + updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
        return new ResponseEntity<>("Đã xóa thành công", HttpStatus.OK);
    }
}
