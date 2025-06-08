package com.polarbookshop.catalog_service.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var book = new Book("1234567890", "Title",
                "Author", 9.90);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(
                book);
        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var book = new Book("a234567890", "Title",
                "Author", 9.90);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage())
                .isEqualTo("The ISBN format must be valid.");
    }
}
