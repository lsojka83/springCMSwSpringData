package pl.springCMS.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.springCMS.entity.Article;
import pl.springCMS.entity.Author;
import pl.springCMS.entity.Category;
import pl.springCMS.repository.ArticleRepository;
import pl.springCMS.repository.AuthorRepository;
import pl.springCMS.repository.CategoryRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;



    public ArticleController(ArticleRepository articleRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/{categoryId}")
//    @Transactional
    public String getArticlesById(@PathVariable String categoryId, Model model) {
        List<Article> articles = new ArrayList<>();
        articles = articleRepository.findByCategoryId(Long.valueOf(categoryId))
        ;
        model.addAttribute("articles",articles);
        return "articlelist";
    }

    @PostMapping("")
    public String getByCategoryId(Model model, @RequestParam String categoryId)
    {
        List<Article> articles = new ArrayList<>();
        articles = articleRepository.findByCategoryId(Long.valueOf(categoryId))
        ;
        model.addAttribute("articles",articles);
        return "articlelist";
    }

    @GetMapping("")
    public String showAll() {
        return "articlelist";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("article", new Article());
        return "articleform";
    }

    @PostMapping("/add")
    public String add(@Valid Article article, BindingResult result, @RequestParam String confirm) {
        if (confirm.equals("no")) {
            return "redirect:/article";
        }
        if (result.hasErrors()) {
            return "articleform";
        }
        if (confirm.equals("yes")) {
            articleRepository.save(article);
        }
        return "redirect:/article";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        return "deleteconfirm";
    }

    @PostMapping("/delete")
    public String delete(Model model, @RequestParam Long id, @RequestParam String confirm) {
        if (confirm.equals("yes")) {
            articleRepository.delete(articleRepository.findById(id).get());
        }
        return "redirect:/article";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("article", articleRepository.findElementById(id));
        return "articleform";
    }

    @PostMapping("/edit")
    public String edit(@Valid Article article, BindingResult result, @RequestParam String confirm) {
        if (confirm.equals("no")) {
            return "redirect:/article";
        }
        if (result.hasErrors()) {
            return "articleform";
        }
        if (confirm.equals("yes")) {
            article.setUpdated(LocalDateTime.now());
            articleRepository.save(article);
        }
        return "redirect:/article";
    }

    @ModelAttribute("articles")
    public List<Article> findAll() {
        return articleRepository.findDistinctAll();
    }

    @ModelAttribute("authors")
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
