package com.example.utils.fotify_sca.issues_byCategory.model.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueListing {
	@JacksonXmlProperty(localName = "Chart" )
	private Chart chart;
	
	
	private String listing ;
	private Integer limit;
}
