package by.tms.projectinterpol.dao;

import by.tms.projectinterpol.entity.News;

import java.time.LocalDate;
import java.util.List;

public interface NewsDAO extends BaseDAO<Long, News> {

    long findNewsAmount();

    List<News> findNewsByTag(String tag);

    List<News> findAllNewsWithOffset(String offset);

    List<News> findNewsByPublicationDate(LocalDate localDate);
}
