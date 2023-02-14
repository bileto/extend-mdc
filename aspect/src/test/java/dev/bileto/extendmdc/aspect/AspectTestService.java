package dev.bileto.extendmdc.aspect;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import static org.hamcrest.MatcherAssert.assertThat;

import org.slf4j.MDC;

import dev.bileto.extendmdc.annotation.ExtendMDC;
import dev.bileto.extendmdc.MDCValue;

public class AspectTestService {
  @ExtendMDC({
    @MDCValue(value = "TestValue1", content = "TestContent1"),
    @MDCValue(value = "TestValue2", content = "TestContent2"), @MDCValue("EmptyValue")
  })
  public void testAnnotationDefinedValues() {
    assertThat(MDC.get("TestValue1"), is(equalTo("TestContent1")));
    assertThat(MDC.get("TestValue2"), is(equalTo("TestContent2")));
    assertThat(MDC.get("EmptyValue"), is(equalTo("")));
  }

  @ExtendMDC
  public void testMethodParameters(@MDCValue("ObjectValue") Object objectParam,
      @MDCValue("StringValue") String stringParam, @MDCValue("IntegerValue") Integer integerParam,
      @MDCValue("LongValue") Long longParam, @MDCValue("FloatValue") Float floatParam,
      @MDCValue("DoubleValue") Double doubleParam, @MDCValue("BooleanValue") Boolean booleanParam,
      String ignoredParam
  ) {
    assertThat(MDC.get("ObjectValue"), is(equalTo(String.valueOf(objectParam))));
    assertThat(MDC.get("StringValue"), is(equalTo(stringParam)));
    assertThat(MDC.get("IntegerValue"), is(equalTo(String.valueOf(integerParam))));
    assertThat(MDC.get("LongValue"), is(equalTo(String.valueOf(longParam))));
    assertThat(MDC.get("FloatValue"), is(equalTo(String.valueOf(floatParam))));
    assertThat(MDC.get("DoubleValue"), is(equalTo(String.valueOf(doubleParam))));
    assertThat(MDC.get("BooleanValue"), is(equalTo(String.valueOf(booleanParam))));
    assertThat(MDC.getCopyOfContextMap().values(), not(hasItem(ignoredParam)));
  }
}
