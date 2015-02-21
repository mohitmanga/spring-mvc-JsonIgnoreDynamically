# spring-mvc-JsonIgnoreDynamically

##### Purpose : 
It can sometimes be useful to filter contextually objects serialized to the HTTP response body.There are situations, where parts of the data returned might not be usefull for the device that is consuming the API's or should remain invisible for the public, and therefore shouldn't be serialized and exposed via JSON. This project aims at the same problem, based on some criteria/business logic we can define whether we want to serialize particular part of response or not.

##### How to Use/How it Works : 
We need to define our Custom Http Message converter, this converter will be responsible for deciding whether particular part of response needs to be serialized or not (Json Response). 
For all Custom java objects that need some kind of filtering we need to define annotations at class level giving list of all the fields that should not serialized for an request.

For example. below we have mentioned that if the request comes from andorid platform than we need to ignore "created" property during serialization.
<pre><code>http://localhost/spring-mvc-jsonignore/sp/rs/message/android</code></pre>
```
@IgnoreFields(sourceFields = {
		@SourceFields(fields = { "created" }, platform = Platform.ANDROID),
		@SourceFields(fields = { "body", "id" }, platform = Platform.IOS) })
public class Message {
	private Long id;
	private Date created;
	private String title;
	private User author;
	private List<User> recipients;
	private String body;
```
In this case for each platform we will have individual message converter, with the default message converter being MappingJackson2HttpMessageConverter
```
	
	/**
	 * Map of all the message converters against the selected
	 * platform
	 */
private ConcurrentHashMap<String, HttpMessageConverter<Object>> messageConverters = new ConcurrentHashMap<String, HttpMessageConverter<Object>>();
	
```

For each message converter we will be having different Objectmapper and each objectmapper will be having its own reference of Custom JacksonAnnotationIntrospector whose role is to provide list of fields that we need to filter during serialization process.

```
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
    ```
