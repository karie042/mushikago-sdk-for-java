package org.mushikago.sdk.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParameter {
  String name();
  boolean isRequired() default false;
  boolean isPartOfURI() default false;
  boolean isMultipart() default false;
}
