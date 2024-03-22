package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = {NoLessThanMinValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidNoLessThanMin {
    String message() default "The inventory of a Part must be more than the minimum.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
