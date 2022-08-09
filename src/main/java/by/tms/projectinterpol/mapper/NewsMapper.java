package by.tms.projectinterpol.mapper;

import by.tms.projectinterpol.dto.NewsDTO;
import by.tms.projectinterpol.entity.News;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public NewsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NewsDTO convertToDTO(News news) {
        return modelMapper.map(news, NewsDTO.class);
    }

    public News convertToNewsEntity(NewsDTO newsDTO) {
        return modelMapper.map(newsDTO, News.class);
    }
}
