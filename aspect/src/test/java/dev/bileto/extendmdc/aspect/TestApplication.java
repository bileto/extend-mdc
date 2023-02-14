package dev.bileto.extendmdc.aspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TestApplication {
  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }
}
