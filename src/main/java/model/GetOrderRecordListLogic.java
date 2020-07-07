package model;

import java.util.List;

import dao.OrderRecordDao;
import dto.OrderRecordDto;
import dto.QueryDto;

public class GetOrderRecordListLogic {

	OrderRecordDao dao = new OrderRecordDao();

	// レコード取得
	public List<OrderRecordDto> execute(QueryDto queryDto) {
		List<OrderRecordDto> orderRecordList = dao.findAll(queryDto);
		return orderRecordList;
	}

	// カラム名取得
	public List<String> executeCulumn() {
		List<String> columnNameList = dao.getColumnName();
		return columnNameList;
	}
}
