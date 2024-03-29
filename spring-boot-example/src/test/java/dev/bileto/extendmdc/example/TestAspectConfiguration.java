package dev.bileto.extendmdc.example;

import dev.bileto.extendmdc.aspect.ExtendMDCAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@EnableAspectJAutoProxy
@Configuration
public class TestAspectConfiguration {
  @Bean
  public ExtendMDCAspect extendMDCAspect() {
    return new ExtendMDCAspect();
  }
}
