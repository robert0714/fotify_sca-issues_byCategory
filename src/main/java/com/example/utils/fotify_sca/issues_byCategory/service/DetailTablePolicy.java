package com.example.utils.fotify_sca.issues_byCategory.service;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy; 
import com.example.utils.fotify_sca.issues_byCategory.model.DetailData;

/**
 * 付款通知书 明细表格的自定义渲染策略<br/>
 */
public class DetailTablePolicy extends DynamicTableRenderPolicy {

	// 货品填充数据所在行数
	int goodsStartRow = 1;

	@Override
	public void render(XWPFTable table, Object data) throws Exception {
		if (null == data)
			return;
		DetailData detailData = (DetailData) data;

		List<RowRenderData> goods = detailData.getGoods();
		if (null != goods) {
			table.removeRow(goodsStartRow);
			for (int i = 0; i < goods.size(); i++) {
				XWPFTableRow insertNewTableRow = table.insertNewTableRow(goodsStartRow);
				for (int j = 0; j < 6; j++) {
					insertNewTableRow.createCell();
				}					
				TableRenderPolicy.Helper.renderRow(table.getRow(goodsStartRow), goods.get(i));
			}
		}
	}

}
