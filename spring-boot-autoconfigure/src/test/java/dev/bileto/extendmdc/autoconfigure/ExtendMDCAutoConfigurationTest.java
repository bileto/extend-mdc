package dev.bileto.extendmdc.autoconfigure;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.bileto.extendmdc.aspect.ExtendMDCAspect;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class)
@Import(AutoConfigureTestConfiguration.class)
public class ExtendMDCAutoConfigurationTest {
  @Test
  public void testAspectBeanIsCreated(ApplicationContext applicationContext) {
    ExtendMDCAspect aspect = applicationContext.getBean(ExtendMDCAspect.class);
    assertNotNull("Aspect Bean not provided from Spring Context", aspect);
  }
}
