package pl.springCMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.springCMS.entity.Category;
import pl.springCMS.repository.CategoryRepository;

import java.util.List;

@Controller
public class HomePageController {

    private final CategoryRepository categoryRepository;


    public HomePageController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public String home()
    {
        return "categorylinkslist";
    }


    @ModelAttribute("categories")
    public List<Category> getCategories()
    {
        return categoryRepository.findAll();
    }

}
