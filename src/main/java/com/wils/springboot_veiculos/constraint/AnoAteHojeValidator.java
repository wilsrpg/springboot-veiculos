package com.wils.springboot_veiculos.constraint;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class AnoAteHojeValidator 
    implements ConstraintValidator<AnoAteHoje, Object> {
  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    if (value == null)
      return true;

    if (!eNumero(value)) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(
        "O parâmetro deve ser um número."
      )
      .addConstraintViolation();
      return false;
    }

    return ((int) value >= 1900) && ((int) value <= LocalDate.now().getYear());
  }

  private boolean eNumero(Object n) {
    try {
      //if (n instanceof Integer)
        //return true;
      //if (n instanceof String) {
        Integer.parseInt(n.toString());
        return true;
      //}
      //return false;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}