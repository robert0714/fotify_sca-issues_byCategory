package com.example.utils.fotify_sca.issues_byCategory.model.source;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class MetaInfo {
	
	@JacksonXmlProperty(localName = "Name" )
	private String name;
	
	@JacksonXmlProperty(localName = "Value" )
	private String value;
}
