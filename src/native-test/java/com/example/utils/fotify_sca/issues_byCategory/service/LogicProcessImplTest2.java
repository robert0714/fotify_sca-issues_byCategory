package com.example.utils.fotify_sca.issues_byCategory.service;
 
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths; 
import java.util.Objects; 

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		  classes = com.example.utils.fotify_sca.issues_byCategory.javaFX.Application.class)
public class LogicProcessImplTest2 {
	private LogicProcessImpl service; 
	 
	
	@BeforeEach
	protected void setUp() throws Exception {
		this.service = new LogicProcessImpl();
	}
	@AfterEach
	protected void tearDown() throws Exception {
	}
	@Test
	public void testProcess()throws Exception {
		URL folderUrl = LogicProcessImplTest2.class.getResource("/examples");
		Path startPath = Paths.get(Objects.requireNonNull(folderUrl).toURI());
		this.service .process(startPath);
	}

}
