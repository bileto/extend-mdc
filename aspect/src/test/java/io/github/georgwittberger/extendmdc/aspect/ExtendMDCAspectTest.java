package io.github.georgwittberger.extendmdc.aspect;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AspectTestConfiguration.class)
public class ExtendMDCAspectTest {
  @Autowired
  private AspectTestService aspectTestService;

  @Test
  public void testAspectAddsAndRemovesAnnotationDefinedValues() {
    assertThat(MDC.get("TestValue1"), is(nullValue()));
    assertThat(MDC.get("TestValue2"), is(nullValue()));
    assertThat(MDC.get("EmptyValue"), is(nullValue()));
    aspectTestService.testAnnotationDefinedValues();
    assertThat(MDC.get("TestValue1"), is(nullValue()));
    assertThat(MDC.get("TestValue2"), is(nullValue()));
    assertThat(MDC.get("EmptyValue"), is(nullValue()));
  }

  @Test
  public void testAspectAddsAndRemovesMethodParameters() {
    assertThat(MDC.get("ObjectValue"), is(nullValue()));
    assertThat(MDC.get("StringValue"), is(nullValue()));
    assertThat(MDC.get("IntegerValue"), is(nullValue()));
    assertThat(MDC.get("LongValue"), is(nullValue()));
    assertThat(MDC.get("FloatValue"), is(nullValue()));
    assertThat(MDC.get("DoubleValue"), is(nullValue()));
    assertThat(MDC.get("BooleanValue"), is(nullValue()));
    aspectTestService.testMethodParameters(new Object(), "Test String", 1, 2L, 3.1F, 4.2, true, "Ignored String");
    assertThat(MDC.get("ObjectValue"), is(nullValue()));
    assertThat(MDC.get("StringValue"), is(nullValue()));
    assertThat(MDC.get("IntegerValue"), is(nullValue()));
    assertThat(MDC.get("LongValue"), is(nullValue()));
    assertThat(MDC.get("FloatValue"), is(nullValue()));
    assertThat(MDC.get("DoubleValue"), is(nullValue()));
    assertThat(MDC.get("BooleanValue"), is(nullValue()));
  }
}
