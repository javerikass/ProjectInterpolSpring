package by.tms.projectinterpol.validator;

import by.tms.projectinterpol.dto.RequestsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RequestsValidator implements Validator {

    private static final String AGE = "age";
    private static final String INVALID_AGE = "Invalid age";
    private static final String ERROR_CODE_INVALID_AGE = "age[invalidAge]";

    @Override
    public boolean supports(Class<?> clazz) {
        return RequestsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof RequestsDTO requestsDTO) {
            if (requestsDTO.getAge() < 0 || requestsDTO.getAge() > 150) {
                errors.rejectValue(AGE, ERROR_CODE_INVALID_AGE, INVALID_AGE);
            }
        }
    }
}
