package by.tms.projectinterpol.converter;

import by.tms.projectinterpol.dto.UserDTO;
import by.tms.projectinterpol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<String, UserDTO> {

    @Autowired
    private UserService userService;

    @Override
    public UserDTO convert(String source) {
        return userService.findUserByUsername(source)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user with username: " + source));
    }
}
