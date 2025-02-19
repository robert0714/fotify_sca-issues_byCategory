package com.example.utils.fotify_sca.issues_byCategory.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.example.utils.fotify_sca.issues_byCategory.model.FortifySCAIssuesByCateGory;
import com.example.utils.fotify_sca.issues_byCategory.model.IssueByCategory;
import com.example.utils.fotify_sca.issues_byCategory.model.source.ReportDefinition;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LogicProcessImpl {
	private ConvertServiceImpl service;
	private WordReportService outputService;
	public LogicProcessImpl() {
		this.service = new ConvertServiceImpl();
		this.outputService = new WordReportService();
	}
	public void process(final Path startPath) {
		try(final InputStream is = getClass().getResourceAsStream("template001.docx") ;) {
			List<Path> xmlFiles = service.findXmlFiles(startPath);
			List<ReportDefinition> rds = service.convert2Xml(xmlFiles);

			Map<String, Map<String, Integer>> data = service.reGroupingAcrossMultiFiles(rds);
			Map<String, String> friorityMap = service.getFriorityMap(rds);
			Map<String, Integer> friorityTotalMap = service.getTotlaFriority(rds);

			System.out.println();
			friorityTotalMap.entrySet().parallelStream().forEach(friority -> {
				System.out.println("%s : %d".formatted(friority.getKey(), friority.getValue()));
			});
			System.out.println();

			Set<Entry<String, Map<String, Integer>>> entrySet = data.entrySet();

			List<IssueByCategory> alist = new ArrayList<>();

			entrySet.forEach(entry -> {
				String groupTitle = entry.getKey();
				String friority = friorityMap.get(groupTitle);

				Map<String, Integer> unitMap = entry.getValue();
				log.debug("groupTitle: %s".formatted(groupTitle));

				final IssueByCategory issueByCategory = new IssueByCategory(groupTitle, friority, unitMap);
				alist.add(issueByCategory);
				System.out.println(issueByCategory.getSummary());

			});
			FortifySCAIssuesByCateGory datas = this.outputService.process(alist);
 
			Configure config = Configure.builder().bind("detail_table", new DetailTablePolicy()).build();
 
			XWPFTemplate template = XWPFTemplate.compile(is, config).render(datas);
			template.writeToFile("example_esg.docx");
		} catch (IOException e) {
			log.error(e.getMessage() , e ) ;
		}	
	}

}
