package by.tms.projectinterpol.mapper;

import by.tms.projectinterpol.dto.TagDTO;
import by.tms.projectinterpol.entity.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public TagMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TagDTO convertToDTO(Tag tag) {
        return modelMapper.map(tag, TagDTO.class);
    }

    public Tag convertToTagEntity(TagDTO tagDTO){
        return modelMapper.map(tagDTO,Tag.class);
    }
}
