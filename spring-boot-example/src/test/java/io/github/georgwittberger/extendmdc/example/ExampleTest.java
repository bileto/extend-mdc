package io.github.georgwittberger.extendmdc.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ExtendWith(OutputCaptureExtension.class)
@Import(TestAspectConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExampleTest {
  @Autowired
  public TestRestTemplate testRestTemplate;

  @Test
  public void testLogMessageContainsMDCValues(CapturedOutput output) {
    //var restTemplate = applicationContext.getBean(TestRestTemplate.class);

    testRestTemplate.getForObject("/Johnny", String.class);
    assertNotNull(output.toString());
    assertEquals("UserName=Johnny", output.toString());
    assertTrue(output.getOut().contains("UserName=Johnny"));
  }
}
