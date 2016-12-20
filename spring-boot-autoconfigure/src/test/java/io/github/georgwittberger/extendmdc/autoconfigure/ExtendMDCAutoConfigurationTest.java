package io.github.georgwittberger.extendmdc.autoconfigure;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.georgwittberger.extendmdc.aspect.ExtendMDCAspect;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AutoConfigureTestConfiguration.class)
public class ExtendMDCAutoConfigurationTest {
  @Autowired
  private ApplicationContext context;

  @Test
  public void testAspectBeanIsCreated() {
    ExtendMDCAspect aspect = context.getBean(ExtendMDCAspect.class);
    assertThat(aspect, is(notNullValue()));
  }
}
