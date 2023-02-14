package dev.bileto.extendmdc.example.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dev.bileto.extendmdc.example.service.ExampleService;

@RestController
public class ExampleController {
  private static final Logger LOG = LoggerFactory.getLogger(ExampleController.class);

  private final ExampleService exampleService;

  public ExampleController(ExampleService exampleService) {
    this.exampleService = exampleService;
  }

  @GetMapping("/")
  public String generalGreeting() {
    return "Hello, unknown friend! Try a request to /your-name for a personal greeting.";
  }

  @GetMapping("/{userName}")
  public String personalGreeting(@PathVariable("userName") String userName) {
    LOG.info("Request a message for a specific username from the service");
    String message = exampleService.getMessage(userName);
    LOG.info("Return the message to the user");

    return message;
  }
}
