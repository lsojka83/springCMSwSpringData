package pl.springCMS.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.springCMS.entity.Author;
import pl.springCMS.repository.AuthorRepository;

public class AuthorConverter implements Converter<String, Author> {

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public Author convert(String id) {
        return authorRepository.findById(Long.valueOf(id)).get();
    }
}
