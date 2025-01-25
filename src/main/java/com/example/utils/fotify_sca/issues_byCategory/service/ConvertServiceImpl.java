package com.example.utils.fotify_sca.issues_byCategory.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.utils.fotify_sca.issues_byCategory.model.source.GroupingSection;
import com.example.utils.fotify_sca.issues_byCategory.model.source.Issue;
import com.example.utils.fotify_sca.issues_byCategory.model.source.ReportDefinition;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ConvertServiceImpl {

	/**
	 * 遞迴找尋目錄及子目錄中的所有 XML 檔案。
	 *
	 * @param targetDir 目標目錄
	 * @return 包含所有 XML 檔案路徑的 List
	 * @throws IOException 如果讀取檔案時發生錯誤
	 */
	public List<Path> findXmlFiles(Path startPath) throws IOException {
		if (!Files.isDirectory(startPath)) {
			throw new IllegalArgumentException("指定的目錄無效: " + startPath);
		}

		// 遞迴處理目錄結構，確保收集所有層級的 XML 檔案
		return Files.walk(startPath) // 遞迴搜尋所有目錄和檔案
				.filter(Files::isRegularFile) // 過濾出檔案（排除目錄）
				.filter(path -> path.toString().endsWith(".xml")) // 過濾出副檔名為 .xml 的檔案
				.filter(path -> !path.toString().contains(".pdf")) // 排除pdf
				.filter(path -> !path.toString().contains(".log")) // 排除log
				.filter(path -> !path.toString().contains(".txt")) // 排除update-request.txt
				.collect(Collectors.toList()); // 收集結果到 List
	}

	public List<ReportDefinition> convert2Xml(List<Path> src) {
		final XmlMapper mapper = XmlMapper.builder().defaultUseWrapper(false)
				// enable/disable Features, change AnnotationIntrospector
				.build();

		final List<ReportDefinition> result = new ArrayList<>();
		src.parallelStream().forEach(path -> {
			try {
				ReportDefinition bean = deserialization(path, mapper);
				result.add(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			;
		});

		return result;
	}

	protected ReportDefinition deserialization(Path path, final XmlMapper mapper) throws IOException {
		String content = Files.readString(path);
		ReportDefinition bean = mapper.readValue(content, ReportDefinition.class);
		bean.setOriginalFileName(path.toFile().getName());
		return bean;
	}

	public  Map<String, Map<String, Integer>> reGroupingAcrossMultiFiles(final List<ReportDefinition> rds) {
		final Map<String, Map<String, Integer>> cacl = new HashMap<>();
		for (final ReportDefinition rd : rds) {
			final String originalFileName = rd.getOriginalFileName();
			List<GroupingSection> groupingSections = rd.getReportSection().get(1).getSubSection().get(1)
					.getIssueListing().getChart().getGroupingSections();		
			
			for (GroupingSection groupingSection : groupingSections) {
				final String groupTitle = groupingSection.getGroupTitle();
				Map<String, Integer> valueOneLevel = cacl.getOrDefault(groupTitle, new HashMap<String, Integer>());
				Integer count = groupingSection.getCount();
				valueOneLevel.put(originalFileName, count);
				cacl.put(groupTitle, valueOneLevel);
			}
		}
		return  cacl;
	}
	public Map<String,String> getFriorityMap(final List<ReportDefinition> rds){	
		final Map<String, String> result = new HashMap<>();
		for (final ReportDefinition rd : rds) {			
			List<GroupingSection> groupingSections = rd.getReportSection().get(2).getSubSection().get(0)
					.getIssueListing().getChart().getGroupingSections();						
			for (GroupingSection groupingSection : groupingSections) {
				final String groupTitle = groupingSection.getGroupTitle();
				for (Issue issue : groupingSection.getIssues()) {
					result.put(groupTitle, issue.getFriority()) ;
				}
			}
		}
		return  result ;
	}
	public Map<String,Integer> getTotlaFriority(final List<ReportDefinition> rds){	
		final Map<String, Integer> result = new HashMap<>();
		for (final ReportDefinition rd : rds) {			
			List<GroupingSection> groupingSections = rd.getReportSection().get(0).getSubSection().get(1)
					.getIssueListing().getChart().getGroupingSections();					
			for (GroupingSection groupingSection : groupingSections) {
				final String groupTitle = groupingSection.getGroupTitle();
				int total = result.getOrDefault(groupTitle, Integer.valueOf(0));
				
				Integer count = groupingSection.getCount() ;
				total = count +total ;
				result.put(groupTitle, total);
			}
		}
		return  result ;
	}
}
