package com.campany.validation.validator;

import com.campany.properties.RegexProperties;
import com.campany.validation.annotation.ValidEmail;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class CustomEmailValidator implements ConstraintValidator<ValidEmail, String> {

    private final RegexProperties regexProperties;
    private Pattern pattern;

    public CustomEmailValidator(RegexProperties regexProperties) {
        this.regexProperties = regexProperties;
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        pattern = Pattern.compile(regexProperties.getEmail());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && pattern.matcher(value).matches();
    }
}
