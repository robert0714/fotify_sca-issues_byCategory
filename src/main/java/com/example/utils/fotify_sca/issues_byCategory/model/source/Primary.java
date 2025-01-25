package com.example.utils.fotify_sca.issues_byCategory.model.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Primary {

	@JacksonXmlProperty(localName = "FileName")
	private String fileName ;
	
	@JacksonXmlProperty(localName = "FilePath")
	private String filePath ;
	
	@JacksonXmlProperty(localName = "LineStart")
	private Integer lineStart ;
	
	@JacksonXmlProperty(localName = "Snippet")
	private String snippet ;
	
	@JacksonXmlProperty(localName = "TargetFunction")
	private String targetFunction ;
	 
}