package by.tms.projectinterpol.converter;

import by.tms.projectinterpol.dto.TagDTO;
import by.tms.projectinterpol.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class TagConverter implements Converter<String, TagDTO> {

    @Autowired
    private TagService tagService;

    @Override
    public TagDTO convert(String source) {
        return tagService.findTagByName(source).orElseThrow(() -> new EntityNotFoundException("Can't find tag with name: " + source));

    }

}