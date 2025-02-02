package com.example.utils.fotify_sca.issues_byCategory.model;

import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class IssueByCategory {
	private final String groupTitle;
	private final String friority;
	private int amount;
	private final Map<String, Integer> reGroupingAcrossOneFile;

	public IssueByCategory(final String category,final String friority , final Map<String, Integer> reGroupingAcrossOneFile) {
		this.groupTitle = category;
		this.friority = friority ;
		this.reGroupingAcrossOneFile = reGroupingAcrossOneFile;
	}

	public String getFriority() {
		return friority;
	}
	public String getGroupTitle() {
		return groupTitle;
	}

	public int getAmount() {
		Set<Entry<String, Integer>> unitEntrySet =  this.reGroupingAcrossOneFile.entrySet();
		int count = 0;
		for (Entry<String, Integer> unitEntry : unitEntrySet) {
			Integer srcFileIssueCount = unitEntry.getValue();
			log.debug("src: %s".formatted(unitEntry.getKey()));
			log.debug("count: %d".formatted(srcFileIssueCount));
			count = count + srcFileIssueCount;
		}
		log.debug("groupTitle: %s , total: %d".formatted(this.groupTitle, count));
		this.amount = count;
		return amount;
	}
	
	public String getSummary() {
		StringBuffer sbf = new StringBuffer("Category: %s ".formatted(this.groupTitle))
				.append(System.lineSeparator() )	
		        .append("Friority: %s ".formatted(this.friority)) 
				.append(System.lineSeparator() ) ;	
		int total =  getAmount() ;
		
		Set<Entry<String, Integer>> unitEntrySet = this.reGroupingAcrossOneFile.entrySet();		 
		for (Entry<String, Integer> unitEntry : unitEntrySet) {
			Integer srcFileIssueCount = unitEntry.getValue();
			sbf.append("Proportion: %s".formatted(decorationV01(unitEntry.getKey())));
			sbf.append(": %d / %d".formatted(srcFileIssueCount, total)).append(System.lineSeparator() ); 
		}
		return sbf.toString();
	} 
	public String getProportion() {
		StringBuffer sbf = new StringBuffer() 
				.append(System.lineSeparator() ) ;	
		int total =  getAmount() ;
		
		Set<Entry<String, Integer>> unitEntrySet = this.reGroupingAcrossOneFile.entrySet();		 
		for (Entry<String, Integer> unitEntry : unitEntrySet) {
			Integer srcFileIssueCount = unitEntry.getValue();
			sbf.append("%s".formatted(decorationV01(unitEntry.getKey())));
			sbf.append(": %d / %d".formatted(srcFileIssueCount, total)).append(System.lineSeparator() ); 
		}
		return sbf.toString();
	} 
	private String decorationV01(String originString) {
		return originString.replace("-DeveloperWorkbook.xml", "");
	}

	
	 
}
