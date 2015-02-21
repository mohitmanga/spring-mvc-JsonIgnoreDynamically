package com.example.messageconverter;

import com.example.annotation.IgnoreFields;
import com.example.annotation.SourceFields;
import com.example.common.Platform;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

/**
 * Custom Jackson Annotaion introspector, used
 * in order to identify list of all the fields
 * that need to be ignored during serialization
 * of an java object
 *
 */
public class CustomJacksonAnnotationIntrospector extends
		JacksonAnnotationIntrospector {

	/**
	 * @see Platform
	 */
	private String platform;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3198014496390608643L;
	
	public CustomJacksonAnnotationIntrospector(String plaform){
		this.platform = plaform;
	}
	
    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector#findPropertiesToIgnore(com.fasterxml.jackson.databind.introspect.Annotated)
     */
    @Override
    public String[] findPropertiesToIgnore(Annotated ac) {
    	IgnoreFields ignoreFields = ac.getAnnotated().getAnnotation(IgnoreFields.class);
    	if(ignoreFields != null){
    		for(SourceFields sourceFields : ignoreFields.sourceFields()){
    			if(sourceFields.platform().name().equalsIgnoreCase(platform)){
    				return sourceFields.fields();
    			}
    		}
    		return super.findPropertiesToIgnore(ac);
    	}else{
    		return super.findPropertiesToIgnore(ac);
    	}
    }

}
