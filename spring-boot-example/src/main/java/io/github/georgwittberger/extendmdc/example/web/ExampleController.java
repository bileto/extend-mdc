package io.github.georgwittberger.extendmdc.example.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.georgwittberger.extendmdc.example.service.ExampleService;

@RestController
public class ExampleController {
  private static final Logger log = LoggerFactory.getLogger(ExampleController.class);

  @Autowired
  private ExampleService exampleService;

  @GetMapping("/")
  public String generalGreeting() {
    return "Hello, unknown friend! Try a request to /your-name for a personal greeting.";
  }

  @GetMapping("/{userName}")
  public String personalGreeting(@PathVariable("userName") String userName) {
    log.info("Request a message for a specific username from the service");
    String message = exampleService.getMessage(userName);
    log.info("Return the message to the user");
    return message;
  }
}
