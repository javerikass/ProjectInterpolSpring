package by.tms.projectinterpol.service;

import by.tms.projectinterpol.dto.NewsDTO;

import java.time.LocalDate;
import java.util.List;

public interface NewsService {

    long save(NewsDTO newsDTO);

    void update(NewsDTO newsDTO);

    void delete(NewsDTO newsDTO);

    long findAmountNews();

    List<NewsDTO> findAll();

    List<NewsDTO> findNewsByTag(String tag);

    List<NewsDTO> findAllNewsWithOffset(String offset);

    List<NewsDTO> findNewsByPublicationDate(LocalDate publicationDate);

}
