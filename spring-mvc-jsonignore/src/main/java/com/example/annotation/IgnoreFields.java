package com.example.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Denotes all fields that need to 
 * be ignored based on selected device
 *
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface IgnoreFields {

	public SourceFields[] sourceFields();

}
