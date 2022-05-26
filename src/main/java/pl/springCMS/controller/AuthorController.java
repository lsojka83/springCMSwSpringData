package pl.springCMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.springCMS.entity.Author;
import pl.springCMS.repository.AuthorRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("")
//    @ResponseBody
    public String showAll(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authorlist";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("author", new Author());
        return "authorform";
    }

    @PostMapping("/add")
    public String add(@Valid Author author, BindingResult result, @RequestParam String confirm) {
        if(confirm.equals("no") ) {
            return "redirect:/author";
        }
        if(result.hasErrors())
        {
            return "authorform";
        }
        if(confirm.equals("yes") ) {
            authorRepository.save(author);
        }
        return "redirect:/author";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        return "deleteconfirm";
    }

    @PostMapping("/delete")
    public String delete(Model model, @RequestParam Long id, @RequestParam String confirm) {
        if (confirm.equals("yes")) {
            authorRepository.delete(authorRepository.findById(id).get());
        }
        return "redirect:/author";    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("author", authorRepository.findById(id));
        return "authorform";
    }

    @PostMapping("/edit")
    public String edit(@Valid Author author, BindingResult result,@RequestParam String confirm) {
        if(confirm.equals("no") ) {
            return "redirect:/author";
        }
        if(result.hasErrors())
        {
            return "authorform";
        }
        if(confirm.equals("yes") )
        {
            authorRepository.save(author);
        }
        return "redirect:/author";
    }

    @ModelAttribute("authors")
    public List<Author> findAll()
    {
        return authorRepository.findAll();
    }
}
