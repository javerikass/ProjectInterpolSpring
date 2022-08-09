package by.tms.projectinterpol.service;

import by.tms.projectinterpol.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    long save(UserDTO userDTO);

    void update(UserDTO userDTO);

    void delete(UserDTO userDTO);

    List<UserDTO> findAllUsers();

    Optional<UserDTO> findById(long id);

    Optional<UserDTO> findUserByUsername(String username);
}
