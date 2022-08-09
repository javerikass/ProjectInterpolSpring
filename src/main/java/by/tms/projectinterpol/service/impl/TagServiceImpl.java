package by.tms.projectinterpol.service.impl;

import by.tms.projectinterpol.dao.TagDAO;
import by.tms.projectinterpol.dto.TagDTO;
import by.tms.projectinterpol.entity.Tag;
import by.tms.projectinterpol.mapper.MapperUtil;
import by.tms.projectinterpol.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDAO tagDAO;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<TagDTO> findTagByName(String tagName) {
        Optional<Tag> tagByName = tagDAO.findTagByName(tagName);
        if (tagByName.isPresent()) {
            return tagByName.map(tag -> (modelMapper.map(tag, TagDTO.class)));
        }
        return Optional.empty();
    }

    @Override
    public long save(TagDTO tagDTO) {
        return tagDAO.save(modelMapper.map(tagDTO, Tag.class));
    }

    @Override
    public void update(TagDTO tagDTO) {
        tagDAO.update(modelMapper.map(tagDTO, Tag.class));
    }

    @Override
    public void delete(TagDTO tagDTO) {
        tagDAO.delete(modelMapper.map(tagDTO, Tag.class));
    }

    @Override
    public List<TagDTO> findAll() {
        return MapperUtil.convertList(tagDAO.findAll(), tag -> (modelMapper.map(tag, TagDTO.class)));
    }
}
