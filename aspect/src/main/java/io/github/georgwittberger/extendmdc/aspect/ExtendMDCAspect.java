package io.github.georgwittberger.extendmdc.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import io.github.georgwittberger.extendmdc.annotation.ExtendMDC;
import io.github.georgwittberger.extendmdc.annotation.MDCValue;

@Aspect
public class ExtendMDCAspect {
  private static final Logger LOG = LoggerFactory.getLogger(ExtendMDCAspect.class);

  @Around("@annotation(io.github.georgwittberger.extendmdc.annotation.ExtendMDC)")
  public Object extendMDC(ProceedingJoinPoint pjp) throws Throwable {
    Method method = ((MethodSignature) pjp.getSignature()).getMethod();
    ExtendMDC methodAnnotation = method.getAnnotation(ExtendMDC.class);
    try {
      addMethodDefinedValues(methodAnnotation);
      addParameterDefinedValues(method.getParameters(), pjp.getArgs());
      return pjp.proceed();
    } finally {
      removeMethodDefinedValues(methodAnnotation);
      removeParameterDefinedValues(method.getParameters());
    }
  }

  private void addMethodDefinedValues(ExtendMDC methodAnnotation) {
    for (MDCValue value : methodAnnotation.value()) {
      LOG.debug(String.format("Putting %s=%s into MDC", value.value(), value.content()));
      // TODO: Backup value from MDC if it already contains a value for the given key
      MDC.put(value.value(), value.content());
    }
  }

  private void removeMethodDefinedValues(ExtendMDC methodAnnotation) {
    for (MDCValue value : methodAnnotation.value()) {
      LOG.debug(String.format("Removing %s=%s from MDC", value.value(), value.content()));
      MDC.remove(value.value());
      // TODO: Restore value to MDC if it already contained a value for the given key
    }
  }

  private void addParameterDefinedValues(Parameter[] parameters, Object[] args) {
    for (int i = 0; i < parameters.length; i++) {
      Parameter parameter = parameters[i];
      MDCValue value = parameter.getAnnotation(MDCValue.class);
      if (value != null) {
        LOG.debug(String.format("Putting %s=%s into MDC", value.value(), value.content()));
        // TODO: Implement formatted output of certain types (e.g. date/time)
        // @MDCValue should provide an optional element "pattern" for that.
        // TODO: Backup value from MDC if it already contains a value for the given key
        MDC.put(value.value(), String.valueOf(args[i]));
      }
    }
  }

  private void removeParameterDefinedValues(Parameter[] parameters) {
    for (int i = 0; i < parameters.length; i++) {
      Parameter parameter = parameters[i];
      MDCValue value = parameter.getAnnotation(MDCValue.class);
      if (value != null) {
        LOG.debug(String.format("Removing %s=%s from MDC", value.value(), value.content()));
        MDC.remove(value.value());
        // TODO: Restore value to MDC if it already contained a value for the given key
      }
    }
  }
}
