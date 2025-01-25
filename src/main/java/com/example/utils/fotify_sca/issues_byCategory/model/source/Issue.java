package com.example.utils.fotify_sca.issues_byCategory.model.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Issue {
	@JacksonXmlProperty(localName = "iid", isAttribute = true)
	private String iid;
	
	@JacksonXmlProperty(localName = "ruleID", isAttribute = true)
	private String ruleID;
	
	@JacksonXmlProperty(localName = "Category")
	private String category;
	
	@JacksonXmlProperty(localName = "Folder")
	private String folder ;
	
	@JacksonXmlProperty(localName = "Kingdom")
	private String kingdom ;
	
	@JacksonXmlProperty(localName = "Abstract")
	private String issueAbstract ;
	
	@JacksonXmlProperty(localName = "Friority")
	private String friority ;
	
	@JacksonXmlProperty(localName = "Primary")
	private Primary primary ;
}
