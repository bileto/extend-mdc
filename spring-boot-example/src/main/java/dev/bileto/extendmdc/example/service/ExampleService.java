package dev.bileto.extendmdc.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.bileto.extendmdc.annotation.ExtendMDC;
import dev.bileto.extendmdc.MDCValue;

@Service
public class ExampleService {
  private static final Logger LOG = LoggerFactory.getLogger(ExampleService.class);

  @ExtendMDC({ @MDCValue(value = "ServiceName", content = "getMessage") })
  public String getMessage(@MDCValue("UserName") String userName) {
    LOG.info("Return a message for a specific username");

    return "Hello, " + userName + "! Nice to meet you.";
  }
}
