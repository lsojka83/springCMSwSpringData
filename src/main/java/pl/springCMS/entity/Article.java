package pl.springCMS.entity;

import pl.springCMS.converter.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200)
    @NotBlank
    @Size(max = 200)
    private String title;
    @NotBlank
    @Size(min = 10)
    private String content;
    @ManyToOne
    private Author author;
    @ManyToMany
    @Size(min = 1)
    private List<Category> categories;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime created;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime updated;

    public Article() {
    }

    public Article(String title) {
        this.title = title;
    }

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return  ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", categories=" + categories +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

}
