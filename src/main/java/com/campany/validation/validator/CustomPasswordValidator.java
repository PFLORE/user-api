package com.campany.validation.validator;

import com.campany.properties.RegexProperties;
import com.campany.validation.annotation.ValidPassword;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class CustomPasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private final RegexProperties regexProperties;
    private Pattern pattern;

    public CustomPasswordValidator(RegexProperties regexProperties) {
        this.regexProperties = regexProperties;
    }

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        pattern = Pattern.compile(regexProperties.getPassword());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && pattern.matcher(value).matches();
    }
}
