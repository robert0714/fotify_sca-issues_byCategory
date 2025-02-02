package com.example.utils.fotify_sca.issues_byCategory.service;

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
class WordReportServiceTest {
	private ConvertServiceImpl service;
	private WordReportService sercie02; 

	@BeforeEach
	protected void setUp() throws Exception {
		service = new ConvertServiceImpl();
		sercie02 = new WordReportService();
	}

	@AfterEach
	protected void tearDown() throws Exception {
	}

	@Test
	public void testProcess()throws Exception {
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
		
		List<IssueByCategory> alist = new ArrayList<>();
		
		entrySet.forEach(entry -> {
			String groupTitle = entry.getKey() ;
			String friority = friorityMap.get(groupTitle);
			
			Map<String, Integer> unitMap = entry.getValue();			
			log.debug("groupTitle: %s".formatted(groupTitle));
			
			final IssueByCategory issueByCategory = new IssueByCategory(groupTitle,friority ,unitMap);
			alist.add(issueByCategory);
			System.out.println(issueByCategory.getSummary());
			
		});
		FortifySCAIssuesByCateGory datas = sercie02.process(alist);
		
		String resource = "src/test/resources/report_templates/template001.docx";
		Configure config = Configure.builder().bind("detail_table", new DetailTablePolicy()).build();
		
	    XWPFTemplate template = XWPFTemplate.compile(resource, config).render(datas);
	    template.writeToFile("target/out_example_payment.docx");		
	}

}