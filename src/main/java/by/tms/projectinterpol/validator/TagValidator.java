package by.tms.projectinterpol.validator;

import by.tms.projectinterpol.dto.TagDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TagValidator implements Validator {

    private static final String TAG_CANNOT_BE_EMPTY = "Tag cannot be empty";
    private static final String TAG = "tag";
    private static final String ERROR_CODE_EMPTY_TAG = "tag[emptyTag]";

    @Override
    public boolean supports(Class<?> clazz) {
        return TagDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof TagDTO tagDTO) {
            if (tagDTO.getTag().isBlank()) {
                errors.rejectValue(TAG, ERROR_CODE_EMPTY_TAG, TAG_CANNOT_BE_EMPTY);
            }
        }
    }
}
