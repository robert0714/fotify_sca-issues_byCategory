package com.example.utils.fotify_sca.issues_byCategory.model.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MajorAttributeSummary {
	@JacksonXmlProperty(localName = "MetaInfo")
	private MetaInfo metaInfo;
}
