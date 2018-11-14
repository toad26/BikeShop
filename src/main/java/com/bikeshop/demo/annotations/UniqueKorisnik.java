package com.bikeshop.demo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bikeshop.demo.validator.KorisnikValidator;

@Constraint(validatedBy = { KorisnikValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueKorisnik {
    String message() default "Email nije jedinstven.";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
    
    String field() default "";
}