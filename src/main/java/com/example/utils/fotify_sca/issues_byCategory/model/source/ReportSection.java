package com.example.utils.fotify_sca.issues_byCategory.model.source;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportSection {
	
	@JacksonXmlProperty(localName ="enabled" , isAttribute = true)
	private String enabled ;
	
	@JacksonXmlProperty(localName ="optionalSubsections" , isAttribute = true)
	private String optionalSubsections ;
	
	@JacksonXmlProperty(localName ="SubSection" , isAttribute = true)
	private List<SubSection> subSection;
	
	@JacksonXmlProperty(localName ="Title"  )
	private String title ;
}
