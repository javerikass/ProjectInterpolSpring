package by.tms.projectinterpol.controller;

import by.tms.projectinterpol.dto.UserDTO;
import by.tms.projectinterpol.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class AdminController {
    @Autowired
    private UserService userService;

    @ModelAttribute("allUsers")
    public List<UserDTO> userDTO() {
        return userService.findAllUsers();
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @PostMapping("/users")
    public String deleteUsers(UserDTO userDTO) {
        userService.delete(userDTO);
        log.info("User {} was deleted",userDTO.getUsername());
        return "redirect:/users";
    }
}
