package com.example.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.example.common.Platform;

/**
 * Used in order to remove provided fields
 * during serialization
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface SourceFields {

	/**
	 * @return Platform
	 */
	public Platform platform();

	/**
	 * @return Array of all the fields to be excluded
	 */
	public String[] fields();
}
