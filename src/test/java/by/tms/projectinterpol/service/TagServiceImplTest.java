package by.tms.projectinterpol.service;

import by.tms.projectinterpol.config.DBConfigTest;
import by.tms.projectinterpol.dto.TagDTO;
import by.tms.projectinterpol.util.TestDataImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DBConfigTest.class)
@Transactional
@WebAppConfiguration
class TagServiceImplTest {

    @Autowired
    private TagService tagService;
    @Autowired
    private TestDataImporter dataImporter;


    @BeforeEach
    public void init() {
        dataImporter.cleanTestData();
        dataImporter.importTestData();
    }

    @Test
    void findTagByName() {
        Optional<TagDTO> murder = tagService.findTagByName("Murder");
        assertNotNull(murder);
    }

    @Test
    void save() {
        tagService.save(TagDTO.builder().tag("Hook").build());
        assertNotNull(tagService.findTagByName("Hook"));
    }

    @Test
    void update() {
        Optional<TagDTO> terror = tagService.findTagByName("Terror");
        if (terror.isPresent()) {
            TagDTO tagDTO = terror.get();
            tagDTO.setTag("Terrror");
            tagService.update(tagDTO);
        }
        assertNotNull(tagService.findTagByName("Terrror"));
    }

    @Test
    void delete() {
        Optional<TagDTO> terror = tagService.findTagByName("Terror");
        tagService.delete(terror.get());
        assertEquals(7, tagService.findAll().size());
    }

    @Test
    void findAll() {
        assertEquals(8, tagService.findAll().size());
    }
}