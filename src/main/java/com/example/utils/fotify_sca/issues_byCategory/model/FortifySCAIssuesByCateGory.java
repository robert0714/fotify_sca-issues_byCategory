package com.example.utils.fotify_sca.issues_byCategory.model;
 
import com.deepoove.poi.expression.Name; 
 
public class FortifySCAIssuesByCateGory {
	@Name("detail_table")
    private DetailData detailTable;

	public DetailData getDetailTable() {
		return detailTable;
	}

	public void setDetailTable(DetailData detailTable) {
		this.detailTable = detailTable;
	} 
	
}
