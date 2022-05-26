package pl.springCMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.springCMS.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
