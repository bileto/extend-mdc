package io.github.georgwittberger.extendmdc.example;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExampleTest {
  @Autowired
  private TestRestTemplate restTemplate;
  @Rule
  public OutputCapture capture = new OutputCapture();

  @Test
  public void testLogMessageContainsMDCValues() {
    restTemplate.getForObject("/Johnny", String.class);
    assertThat(capture.toString(), containsString("UserName=Johnny"));
  }
}
