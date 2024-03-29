package dev.bileto.extendmdc.autoconfigure;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.Advice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import dev.bileto.extendmdc.aspect.ExtendMDCAspect;

@Configuration
@ConditionalOnClass({ ExtendMDCAspect.class, EnableAspectJAutoProxy.class, Aspect.class, Advice.class })
public class ExtendMDCAutoConfiguration {
  @Bean
  public ExtendMDCAspect extendMDCAspect() {
    return new ExtendMDCAspect();
  }
}
