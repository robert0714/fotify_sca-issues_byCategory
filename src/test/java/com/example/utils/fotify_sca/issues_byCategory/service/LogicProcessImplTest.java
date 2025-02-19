package com.example.utils.fotify_sca.issues_byCategory.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.example.utils.fotify_sca.issues_byCategory.model.FortifySCAIssuesByCateGory;
import com.example.utils.fotify_sca.issues_byCategory.model.IssueByCategory;
import com.example.utils.fotify_sca.issues_byCategory.model.source.ReportDefinition;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LogicProcessImplTest {
	private LogicProcessImpl service; 
	
	
	public LogicProcessImplTest() {
		this.service = new LogicProcessImpl(); 
	}
	
	@BeforeEach
	protected void setUp() throws Exception {
		this.service = new LogicProcessImpl();
	}
	@AfterEach
	protected void tearDown() throws Exception {
	}
	@Test
	public void testProcess()throws Exception {
		URL folderUrl = ConvertServiceImplTest.class.getResource("/examples");
		Path startPath = Paths.get(Objects.requireNonNull(folderUrl).toURI());
		this.service .process(startPath);
	}

}
