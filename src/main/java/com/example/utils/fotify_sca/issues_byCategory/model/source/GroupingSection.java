package com.example.utils.fotify_sca.issues_byCategory.model.source;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data 
public class GroupingSection {
	
	private String groupTitle;
	
	@JacksonXmlProperty(localName = "count", isAttribute = true)
	private Integer count;
	
	@JacksonXmlProperty(localName = "MajorAttributeSummary" )
	private MajorAttributeSummary majorAttributeSummary ;
	
	
	@JacksonXmlProperty(localName = "Issue" )
	private List<Issue> issues;
	
}
