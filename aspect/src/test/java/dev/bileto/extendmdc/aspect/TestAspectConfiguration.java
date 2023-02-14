package dev.bileto.extendmdc.aspect;

import dev.bileto.extendmdc.aspect.ExtendMDCAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TestAspectConfiguration {
  @Bean
  public ExtendMDCAspect extendMDCAspect() {
    return new ExtendMDCAspect();
  }

  @Bean
  public AspectTestService aspectTestService() { return new AspectTestService(); }
}
