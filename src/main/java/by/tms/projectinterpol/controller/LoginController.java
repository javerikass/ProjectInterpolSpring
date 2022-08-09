package by.tms.projectinterpol.controller;

import by.tms.projectinterpol.dto.UserDTO;
import by.tms.projectinterpol.entity.Role;
import by.tms.projectinterpol.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@SessionAttributes({"sessionUser"})
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Validator userValidator;
    @Autowired
    private AuthenticationManager authenticationManager;

    @ModelAttribute("sessionUser")
    public UserDTO sessionUser() {
        return new UserDTO();
    }

    @GetMapping("/")
    public String interpol() {
        return "interpol";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String registrationUser(@Valid UserDTO userDTO, Errors errors) {
        userValidator.validate(userDTO, errors);
        if (errors.hasErrors()) {
            return "registrationPage";
        }
        userDTO.setRole(Role.USER);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.save(userDTO);
        log.info("User {} has registered",userDTO.getUsername());
        return "redirect: /loginPage";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @PostMapping("/loginAction")
    public String loginAction(@ModelAttribute("sessionUser") UserDTO userDTO, HttpServletRequest request, Model model) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userService.findUserByUsername(userDTO.getUsername()).ifPresent(user -> userDTO.setRole(user.getRole()));
        request.getSession().setAttribute("sessionUser", userDTO);
        model.addAttribute("sessionUsers", userDTO);
        return "redirect:/";
    }
}
