package pl.springCMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.springCMS.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
