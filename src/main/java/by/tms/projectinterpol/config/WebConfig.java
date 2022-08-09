package by.tms.projectinterpol.config;

import by.tms.projectinterpol.converter.TagConverter;
import by.tms.projectinterpol.converter.UserConverter;
import by.tms.projectinterpol.mapper.MapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("by.tms.projectinterpol.controller")
@Import({ThymeleafConfig.class, InternationalizationConfig.class, MapperUtil.class})
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);
        registry.addConverter(tagConverter());
        registry.addConverter(userConverter());
    }

    @Bean
    public TagConverter tagConverter() {
        return new TagConverter();
    }

    @Bean
    public UserConverter userConverter() {
        return new UserConverter();
    }

}
