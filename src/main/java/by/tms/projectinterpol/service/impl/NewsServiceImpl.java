package by.tms.projectinterpol.service.impl;

import by.tms.projectinterpol.dao.NewsDAO;
import by.tms.projectinterpol.dto.NewsDTO;
import by.tms.projectinterpol.entity.News;
import by.tms.projectinterpol.mapper.MapperUtil;
import by.tms.projectinterpol.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long save(NewsDTO newsDTO) {
        return newsDAO.save(modelMapper.map(newsDTO, News.class));
    }

    @Override
    public void update(NewsDTO newsDTO) {
        newsDAO.update(modelMapper.map(newsDTO, News.class));
    }

    @Override
    public void delete(NewsDTO newsDTO) {
        newsDAO.delete(modelMapper.map(newsDTO, News.class));
    }

    @Override
    public long findAmountNews() {
        long newsAmount = newsDAO.findNewsAmount();
        log.info("Amount news: " + newsAmount);
        return newsAmount;
    }

    @Override
    public List<NewsDTO> findAllNewsWithOffset(String offset) {
        return MapperUtil.convertList(newsDAO.findAllNewsWithOffset(offset), news -> (modelMapper.map(news, NewsDTO.class)));
    }

    @Override
    public List<NewsDTO> findAll() {
        return MapperUtil.convertList(newsDAO.findAll(), news -> (modelMapper.map(news, NewsDTO.class)));
    }

    @Override
    public List<NewsDTO> findNewsByTag(String tag) {
        return MapperUtil.convertList(newsDAO.findNewsByTag(tag), news -> (modelMapper.map(news, NewsDTO.class)));
    }

    @Override
    public List<NewsDTO> findNewsByPublicationDate(LocalDate publicationDate) {
        return MapperUtil.convertList(newsDAO.findNewsByPublicationDate(publicationDate), news -> (modelMapper.map(news, NewsDTO.class)));
    }
}
