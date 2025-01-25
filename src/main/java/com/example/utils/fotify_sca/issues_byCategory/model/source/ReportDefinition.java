package com.example.utils.fotify_sca.issues_byCategory.model.source;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportDefinition {
	
	@JacksonXmlProperty(localName ="type" , isAttribute = true)
	private String type ;
	
	@JacksonXmlProperty(localName ="ReportSection")
	private List<ReportSection> reportSection ;
	
	private String originalFileName;
	 
}
