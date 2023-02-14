package dev.bileto.extendmdc.annotation;

import dev.bileto.extendmdc.MDCValue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtendMDC {
  MDCValue[] value() default {};
}
