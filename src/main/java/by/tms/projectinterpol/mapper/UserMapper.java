package by.tms.projectinterpol.mapper;

import by.tms.projectinterpol.dto.UserDTO;
import by.tms.projectinterpol.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
