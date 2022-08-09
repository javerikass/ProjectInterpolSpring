package by.tms.projectinterpol.service;

import by.tms.projectinterpol.config.DBConfigTest;
import by.tms.projectinterpol.dto.NewsDTO;
import by.tms.projectinterpol.util.TestDataImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DBConfigTest.class)
@Transactional
@WebAppConfiguration
class NewsServiceImplTest {

    @Autowired
    private NewsService newsService;
    @Autowired
    private TestDataImporter dataImporter;


    @BeforeEach
    public void init() {
        dataImporter.cleanTestData();
        dataImporter.importTestData();
    }

    @Test
    void save() {
        long id = newsService.save(NewsDTO.builder().text("News").publicationDate(LocalDate.now()).headline("Headline").build());
        assertNotNull(id);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findNewsAmount() {
        assertEquals(5, newsService.findAmountNews());
    }

    @Test
    void findAllNewsWithOffset() {
        int size = newsService.findAllNewsWithOffset("2").size();
        assertEquals(3, size);
    }

    @Test
    void findAll() {
        int size = newsService.findAll().size();
        assertEquals(5, size);
    }

    @Test
    void findNewsByTag() {
        int size = newsService.findNewsByTag("Terror").size();
        assertEquals(2, size);
    }

    @Test
    void findNewsByPublicationDate() {
        int size = newsService.findNewsByPublicationDate(LocalDate.of(2022, Month.APRIL, 10)).size();
        assertEquals(2, size);
    }
}