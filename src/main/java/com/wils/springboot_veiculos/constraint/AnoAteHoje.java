package com.wils.springboot_veiculos.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * O elemento anotado deve estar entre {@code min} e o ano atual (inclusive).
 * Aceita {@code int} e {@code CharSequence}.
 *
 * @author Wilson Belém
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AnoAteHojeValidator.class)
public @interface AnoAteHoje {

  String message() default "O ano deve estar entre o valor do campo 'min' e o ano atual (inclusive).";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

  int min();
	//int max();
}