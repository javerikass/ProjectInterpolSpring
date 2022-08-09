package by.tms.projectinterpol.validator;

import by.tms.projectinterpol.dto.UserDTO;
import by.tms.projectinterpol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private static final String USERNAME_FIELD_IS_EMPTY = "Username field is empty";
    private static final String USERNAME_LENGTH_IS_INVALID = "Username length is invalid";
    private static final String USERNAME_IS_ALREADY_TAKEN = "Username is already taken";
    private static final String INVALID_CHARACTERS_IN_USERNAME = "Invalid characters in username";

    private static final String PASSWORD_FIELD_IS_EMPTY = "Password field is empty";
    private static final String PASSWORD_LENGTH_IS_INVALID = "Password length is invalid";
    private static final String INVALID_CHARACTERS_IN_PASSWORD = "Invalid characters in password";

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final String ERROR_CODE_EMPTY_USERNAME = "username[emptyUsername]";
    private static final String ERROR_CODE_USERNAME_INVALID_LENGTH = "username[invalidLength]";
    private static final String ERROR_CODE_USERNAME_INVALID_PATTERN = "username[invalidPattern]";
    private static final String ERROR_CODE_USERNAME_PRESENT = "username[presentUsername]";
    private static final String ERROR_CODE_EMPTY_PASSWORD = "password[emptyPassword]";
    private static final String ERROR_CODE_PASSWORD_INVALID_LENGTH = "password[invalidLength]";
    private static final String ERROR_CODE_PASSWORD_INVALID_PATTERN = "password[invalidPattern]";

    @Autowired
    private UserService userService;

    public static boolean patternMatches(String value) {
        return Pattern.matches("^[A-Za-z\\d]{4,16}$", value);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof UserDTO userDTO) {
            if (userDTO.getUsername().isBlank()) {
                errors.rejectValue(USERNAME, ERROR_CODE_EMPTY_USERNAME, USERNAME_FIELD_IS_EMPTY);
            } else if (userDTO.getUsername().length() < 4 || userDTO.getUsername().length() > 16) {
                errors.rejectValue(USERNAME, ERROR_CODE_USERNAME_INVALID_LENGTH, USERNAME_LENGTH_IS_INVALID);
            } else if (!patternMatches(userDTO.getUsername())) {
                errors.rejectValue(USERNAME, ERROR_CODE_USERNAME_INVALID_PATTERN, INVALID_CHARACTERS_IN_USERNAME);
            } else if (userService.findUserByUsername(userDTO.getUsername()).isPresent()) {
                errors.rejectValue(USERNAME, ERROR_CODE_USERNAME_PRESENT, USERNAME_IS_ALREADY_TAKEN);
            }

            if (userDTO.getPassword().isBlank()) {
                errors.rejectValue(PASSWORD, ERROR_CODE_EMPTY_PASSWORD, PASSWORD_FIELD_IS_EMPTY);
            } else if (userDTO.getPassword().length() < 4 || userDTO.getPassword().length() > 16) {
                errors.rejectValue(PASSWORD, ERROR_CODE_PASSWORD_INVALID_LENGTH, PASSWORD_LENGTH_IS_INVALID);
            } else if (!patternMatches(userDTO.getPassword())) {
                errors.rejectValue(PASSWORD, ERROR_CODE_PASSWORD_INVALID_PATTERN, INVALID_CHARACTERS_IN_PASSWORD);
            }
        }
    }
}
