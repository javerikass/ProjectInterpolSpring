package by.tms.projectinterpol.validator;

import by.tms.projectinterpol.dto.NewsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewsValidator implements Validator {

    private static final String NEWS_IS_EMPTY = "News can't be empty";
    private static final String HEADLINE_IS_EMPTY = "Headline can't be empty";
    private static final String TEXT = "text";
    private static final String HEADLINE = "headline";
    private static final String ERROR_CODE_EMPTY_NEWS = "text[emptyNews]";
    private static final String ERROR_CODE_EMPTY_HEADLINE = "headline[emptyHeadline]";

    @Override
    public boolean supports(Class<?> clazz) {
        return NewsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof NewsDTO newsDTO) {
            if (newsDTO.getText().isBlank()) {
                errors.rejectValue(TEXT, ERROR_CODE_EMPTY_NEWS, NEWS_IS_EMPTY);
            } else if (newsDTO.getHeadline().isBlank()) {
                errors.rejectValue(HEADLINE, ERROR_CODE_EMPTY_HEADLINE, HEADLINE_IS_EMPTY);
            }
        }
    }
}
