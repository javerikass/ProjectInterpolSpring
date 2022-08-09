package by.tms.projectinterpol.service;

import by.tms.projectinterpol.dto.TagDTO;

import java.util.List;
import java.util.Optional;

public interface TagService {

    long save(TagDTO tagDTO);

    void update(TagDTO tagDTO);

    void delete(TagDTO tagDTO);

    List<TagDTO> findAll();

    Optional<TagDTO> findTagByName(String tagName);
}
