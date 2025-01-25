package com.example.utils.fotify_sca.issues_byCategory.model.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubSection {
	@JacksonXmlProperty(localName = "IssueListing" )
	private IssueListing issueListing;

	@JacksonXmlProperty(localName = "enabled", isAttribute = true)
	private String enabled;
}
