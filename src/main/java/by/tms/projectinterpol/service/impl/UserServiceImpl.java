package by.tms.projectinterpol.service.impl;

import by.tms.projectinterpol.dao.UserDAO;
import by.tms.projectinterpol.dto.UserDTO;
import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.mapper.MapperUtil;
import by.tms.projectinterpol.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ModelMapper modelMapper;

    private final Function<User, UserDetails> userToUserDetails = user -> org.springframework.security.core.userdetails.User
            .builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .authorities(user.getRole().name())
            .build();

    @Override
    public long save(UserDTO userDTO) {
        return userDAO.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public Optional<UserDTO> findById(long id) {
        Optional<User> userById = userDAO.findById(id);
        if (userById.isPresent()) {
            return userById.map(u -> (modelMapper.map(u, UserDTO.class)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findUserByUsername(String username) {
        Optional<User> maybeUser = userDAO.findUserByUsername(username);
        if (maybeUser.isPresent()) {
            return maybeUser.map(u -> (modelMapper.map(u, UserDTO.class)));
        }
        return Optional.empty();
    }

    @Override
    public void delete(UserDTO userDTO) {
        userDAO.delete(modelMapper.map(userDTO, User.class));
    }

    @Override
    public void update(UserDTO userDTO) {
        userDAO.update(modelMapper.map(userDTO, User.class));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return MapperUtil.convertList(userDAO.findAll(), user -> (modelMapper.map(user, UserDTO.class)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDAO.findUserByUsername(username);
        return user
                .map(userToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user with username: " + username));
    }
}
