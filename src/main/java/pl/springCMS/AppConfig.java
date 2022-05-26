package pl.springCMS;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.springCMS.converter.AuthorConverter;
import pl.springCMS.converter.CategoryConverter;

@Configuration
@ComponentScan("pl.springCMS")
@EnableWebMvc
@EnableJpaRepositories("pl.springCMS.repository")
public class AppConfig implements WebMvcConfigurer{

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry)
    {
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);
        registry.addConverter(authorConverter());
        registry.addConverter(categoryConverter());
    }

    @Bean
    public Converter authorConverter() {
        return new AuthorConverter();
    }

    @Bean
    public Converter categoryConverter() {
        return new CategoryConverter();
    }


}
