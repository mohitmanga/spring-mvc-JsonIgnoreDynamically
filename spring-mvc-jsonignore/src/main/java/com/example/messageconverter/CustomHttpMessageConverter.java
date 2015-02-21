package com.example.messageconverter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Custom Http Message converter
 */
public class CustomHttpMessageConverter implements HttpMessageConverter<Object> {

	/**
	 * Default Message converter. Current being used is
	 * org.springframework.http.converter.json.MappingJackson2HttpMessageConverter.MappingJackson2HttpMessageConverter()
	 */
	private HttpMessageConverter<Object> defaultMessageConverter;
	
	/**
	 * Map of all the message converters against the selected
	 * platform
	 */
	private ConcurrentHashMap<String, HttpMessageConverter<Object>> messageConverters = new ConcurrentHashMap<String, HttpMessageConverter<Object>>();
	
	/**
	 * Proxy object of Httpservlet request
	 */
	@Autowired
	private HttpServletRequest request;
	
	public CustomHttpMessageConverter() {
	}

	public CustomHttpMessageConverter( HttpMessageConverter<Object> defHttpMessageConverter) {
		this.defaultMessageConverter = defHttpMessageConverter;
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.HttpMessageConverter#canRead(java.lang.Class, org.springframework.http.MediaType)
	 */
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return defaultMessageConverter.canRead(clazz, mediaType);
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.HttpMessageConverter#canWrite(java.lang.Class, org.springframework.http.MediaType)
	 */
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return defaultMessageConverter.canWrite(clazz, mediaType);
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.HttpMessageConverter#getSupportedMediaTypes()
	 */
	public List<MediaType> getSupportedMediaTypes() {
		return defaultMessageConverter.getSupportedMediaTypes();
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.HttpMessageConverter#read(java.lang.Class, org.springframework.http.HttpInputMessage)
	 */
	public Object read(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return defaultMessageConverter.read(clazz, inputMessage);
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.HttpMessageConverter#write(java.lang.Object, org.springframework.http.MediaType, org.springframework.http.HttpOutputMessage)
	 */
	public void write(Object object, MediaType contentType,
			HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		HttpMessageConverter<Object> httpMessageConverter =  getMessageConverter();
		httpMessageConverter.write(object, contentType, outputMessage);
	}
	
	/**
	 * Used to find message converter that will be used for
	 * serialization
	 * @return Message converter
	 */
	private HttpMessageConverter<Object> getMessageConverter(){
		String key = getSourceFromPathURI();
		HttpMessageConverter<Object> messageConverter = messageConverters.get(key);
		if(messageConverter != null){
			return messageConverter;
		}else{
			ObjectMapper objectMapper = new ObjectMapper();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			objectMapper.setAnnotationIntrospector(new CustomJacksonAnnotationIntrospector(getSourceFromPathURI()));
			converter.setObjectMapper(objectMapper);
			messageConverters.putIfAbsent(key, converter);
			return converter;
		}
	}
	
	/**
	 * Identify platform provided in request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getSourceFromPathURI(){
		Map<String, String> pathVariableMap = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if(pathVariableMap != null){
			return pathVariableMap.get("src");
		}else{
			return "";
		}
	}
}
