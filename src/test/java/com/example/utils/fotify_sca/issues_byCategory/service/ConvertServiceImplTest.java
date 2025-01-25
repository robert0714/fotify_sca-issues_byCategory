package com.example.utils.fotify_sca.issues_byCategory.service;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.utils.fotify_sca.issues_byCategory.model.IssueByCategory;
import com.example.utils.fotify_sca.issues_byCategory.model.source.ReportDefinition;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ConvertServiceImplTest {
	private ConvertServiceImpl service;

	@BeforeEach
	protected void setUp() throws Exception {
		service = new ConvertServiceImpl();
	}

	@AfterEach
	protected void tearDown() throws Exception {
	}

	@Test
	public void testFindXmlFiles() throws Exception {
		URL folderUrl = ConvertServiceImplTest.class.getResource("/examples");
		Path startPath = Paths.get(Objects.requireNonNull(folderUrl).toURI());

		// 找到所有 XML 檔案
		List<Path> xmlFiles = service.findXmlFiles(startPath);

		if (xmlFiles.isEmpty()) {
			System.out.println("未找到任何 XML 檔案。");
		} else {
			// 打印所有 XML 檔案路徑
			xmlFiles.forEach(System.out::println);
		}
	}

	@Test
	public void testConvert2Xml() throws Exception {
		URL folderUrl = ConvertServiceImplTest.class.getResource("/examples");
		Path startPath = Paths.get(Objects.requireNonNull(folderUrl).toURI());
		List<Path> xmlFiles = service.findXmlFiles(startPath);
		service.convert2Xml(xmlFiles);
	}

	@Test
	public void testReGroupingAcrossMultiFiles() throws Exception {
		URL folderUrl = ConvertServiceImplTest.class.getResource("/examples");
		Path startPath = Paths.get(Objects.requireNonNull(folderUrl).toURI());
		List<Path> xmlFiles = service.findXmlFiles(startPath);
		List<ReportDefinition> rds = service.convert2Xml(xmlFiles);
		
		Map<String, Map<String, Integer>> data = service.reGroupingAcrossMultiFiles(rds);
		Map<String,String> friorityMap = service.getFriorityMap(rds);	
		Map<String,Integer> friorityTotalMap = service.getTotlaFriority(rds);
		
		System.out.println();
		friorityTotalMap.entrySet().parallelStream().forEach(friority-> {			
			System.out.println("%s : %d".formatted(friority.getKey() ,friority.getValue()));			
		});
		System.out.println();
		
		Set<Entry<String, Map<String, Integer>>> entrySet = data.entrySet();

		entrySet.forEach(entry -> {
			String groupTitle = entry.getKey() ;
			String friority = friorityMap.get(groupTitle);
			
			Map<String, Integer> unitMap = entry.getValue();			
			log.debug("groupTitle: %s".formatted(groupTitle));
			
			final IssueByCategory issueByCategory = new IssueByCategory(groupTitle,friority ,unitMap);
			 
			System.out.println(issueByCategory.getSummary());
			
		});
	}

}
