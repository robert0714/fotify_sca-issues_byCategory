package com.example.utils.fotify_sca.issues_byCategory.model.source;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chart {
	@JacksonXmlProperty(localName = "chartType", isAttribute = true)
	private String chartType;
	
	@JacksonXmlProperty(localName = "GroupingSection" )
	private List<GroupingSection> groupingSections;
	
}

