package io.github.georgwittberger.extendmdc.aspect;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class)
@Import(TestAspectConfiguration.class)
@ContextConfiguration(classes = TestAspectConfiguration.class)
public class ExtendMDCAspectTests {
  @Test
  public void testAspectAddsAndRemovesAnnotationDefinedValues(ApplicationContext applicationContext) {
    var aspectTestService= applicationContext.getBean(AspectTestService.class);

    assertNull(MDC.get("TestValue1"));
    assertNull(MDC.get("TestValue2"));
    assertNull(MDC.get("EmptyValue"));
    aspectTestService.testAnnotationDefinedValues();
    assertNull(MDC.get("TestValue1"));
    assertNull(MDC.get("TestValue2"));
    assertNull(MDC.get("EmptyValue"));
  }

  @Test
  public void testAspectAddsAndRemovesMethodParameters(ApplicationContext applicationContext) {
    var aspectTestService= applicationContext.getBean(AspectTestService.class);

    assertNull(MDC.get("ObjectValue"));
    assertNull(MDC.get("StringValue"));
    assertNull(MDC.get("IntegerValue"));
    assertNull(MDC.get("LongValue"));
    assertNull(MDC.get("FloatValue"));
    assertNull(MDC.get("DoubleValue"));
    assertNull(MDC.get("BooleanValue"));
    aspectTestService.testMethodParameters(new Object(), "Test String", 1, 2L, 3.1F, 4.2, true, "Ignored String");
    assertNull(MDC.get("ObjectValue"));
    assertNull(MDC.get("StringValue"));
    assertNull(MDC.get("IntegerValue"));
    assertNull(MDC.get("LongValue"));
    assertNull(MDC.get("FloatValue"));
    assertNull(MDC.get("DoubleValue"));
    assertNull(MDC.get("BooleanValue"));
  }
}
