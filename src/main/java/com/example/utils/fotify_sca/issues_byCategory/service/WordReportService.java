package com.example.utils.fotify_sca.issues_byCategory.service;

import java.util.ArrayList; 
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows; 
import com.example.utils.fotify_sca.issues_byCategory.model.FortifySCAIssuesByCateGory;
import com.example.utils.fotify_sca.issues_byCategory.model.IssueByCategory;
import com.example.utils.fotify_sca.issues_byCategory.model.DetailData ;


public class WordReportService {
	public FortifySCAIssuesByCateGory process(final List<IssueByCategory> data) {
		FortifySCAIssuesByCateGory result =new FortifySCAIssuesByCateGory();		
		DetailData detailTable = new DetailData();
		List<RowRenderData> goods = new ArrayList<>();
		for (int i = ( data.size()-1); -1< i && i < data.size(); --i) {
			 IssueByCategory unit = data.get(i);
//			 String[] titles = StringUtils.split(unit.getGroupTitle(),":") ; 
//			 
//			 String riskNameContent = titles[0] + System.lineSeparator() +unit.getProportion();
			 String riskNameContent = unit.getGroupTitle() + System.lineSeparator() +unit.getProportion();
 	 
			 RowRenderData good = Rows.of( String.valueOf(1+i), riskNameContent,  unit.getFriority(), "", "/", "" ).center().create();
			 goods.add(good);
			 
		} 
         
        detailTable.setGoods(goods);
        result.setDetailTable(detailTable);
        
		return result ; 
	}
}
