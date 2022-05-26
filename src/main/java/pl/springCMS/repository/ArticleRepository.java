package pl.springCMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.springCMS.entity.Article;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a LEFT JOIN FETCH a.categories cats WHERE cats.id = :categoryId ")
    List<Article> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT DISTINCT a FROM Article a  LEFT JOIN FETCH a.categories")
    List<Article> findDistinctAll();

    @Query("SELECT a FROM Article a LEFT JOIN FETCH a.categories WHERE  a.id = :id")
    Article findElementById(@Param("id") Long id);
}
