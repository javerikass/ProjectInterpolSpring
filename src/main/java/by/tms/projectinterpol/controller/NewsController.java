package by.tms.projectinterpol.controller;

import by.tms.projectinterpol.dto.NewsDTO;
import by.tms.projectinterpol.dto.TagDTO;
import by.tms.projectinterpol.service.NewsService;
import by.tms.projectinterpol.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class NewsController {

    private static final String NEWS_PAGE = "createNews";
    @Autowired
    private TagService tagService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private Validator newsValidator;
    @Autowired
    private Validator tagValidator;

    @ModelAttribute("allTags")
    public List<TagDTO> allTags() {
        return tagService.findAll();
    }

    @ModelAttribute("allNews")
    public List<NewsDTO> allNews() {
        return newsService.findAll();
    }

    @ModelAttribute("tag")
    public TagDTO tagDTO() {
        return new TagDTO();
    }

    @ModelAttribute("news")
    public NewsDTO newsDTO() {
        return new NewsDTO();
    }

    @GetMapping("/news/{offset}")
    public String news(Model model, @PathVariable String offset) {
        model.addAttribute("newsPages", (long) Math.ceil(newsService.findAmountNews() / 5.0));
        model.addAttribute("allNews", newsService.findAllNewsWithOffset(offset));
        return "news";
    }

    @GetMapping("/createNews")
    public String creatingNews() {
        return NEWS_PAGE;
    }

    @PostMapping("/deleteTag")
    public String deleteTag(TagDTO tagDTO){
        tagService.delete(tagDTO);
        return NEWS_PAGE;
    }

    @PostMapping("/createTag")
    public String createTag(@Valid @ModelAttribute("tag") TagDTO tagDTO, Errors errors) {
        tagValidator.validate(tagDTO, errors);
        if (errors.hasErrors()) {
            return NEWS_PAGE;
        }
        tagService.save(tagDTO);
        return "redirect: /createNews";
    }

    @PostMapping("/createNews")
    public String createNews(@Valid @ModelAttribute("news") NewsDTO newsDTO, Errors errors) {
        newsValidator.validate(newsDTO, errors);
        if (errors.hasErrors()) {
            return NEWS_PAGE;
        }
        newsDTO.setPublicationDate(LocalDate.now());
        newsService.save(newsDTO);
        return "redirect: /createNews";
    }
}
