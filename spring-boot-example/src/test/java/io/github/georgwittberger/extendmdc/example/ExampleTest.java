package io.github.georgwittberger.extendmdc.example;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import io.github.georgwittberger.extendmdc.example.service.ExampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Import(TestAspectConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExampleTest {
  @Autowired
  public TestRestTemplate testRestTemplate;

  private ListAppender<ILoggingEvent> logWatcher;

  @BeforeEach
  public void setupTest() {
    logWatcher = new ListAppender<>();
    logWatcher.start();
    ((Logger) LoggerFactory.getLogger(ExampleService.class)).addAppender(logWatcher);
  }

  @Test
  public void testLogMessageContainsMDCValues() {
    testRestTemplate.getForObject("/Johnny", String.class);

    assertTrue(
        logWatcher.list.stream()
            .anyMatch(event -> {
              var mdc = event.getMDCPropertyMap();

              return mdc.containsKey("UserName") && mdc.get("UserName") != null && mdc.get("UserName").equals("Johnny");
            }),
        "MDC does not contain UserName=Johnny"
    );
  }
}
