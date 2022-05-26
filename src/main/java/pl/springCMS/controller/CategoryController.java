package pl.springCMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.springCMS.entity.Category;
import pl.springCMS.repository.CategoryRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public String showAll(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/add")
    public String add(@Valid Category category, BindingResult result, @RequestParam String confirm) {
        if(confirm.equals("no"))
        {
            return "categorylist";
        }
        if(result.hasErrors())
        {
            return "categoryform";
        }
        if(confirm.equals("yes")) {
            categoryRepository.save(category);
        }
        return "redirect:/category";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        return "deleteconfirm";
    }

    @PostMapping("/delete")
    public String delete(Model model, @RequestParam Long id, @RequestParam String confirm) {
        if (confirm.equals("yes")) {
            categoryRepository.delete(categoryRepository.findById(id).get());
        }
        return "redirect:/category";    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("category", categoryRepository.findById(id));
        return "categoryform";
    }

    @PostMapping("/edit")
    public String edit(@Valid Category category, BindingResult result, @RequestParam String confirm) {
        if(confirm.equals("no"))
        {
            return "categorylist";
        }
        if(result.hasErrors())
        {
            return "categoryform";
        }
        if(confirm.equals("yes")) {
            categoryRepository.save(category);
        }
        return "redirect:/category";
    }

    @ModelAttribute("categories")
    public List<Category> findAll()
    {
        return categoryRepository.findAll();
    }
}
