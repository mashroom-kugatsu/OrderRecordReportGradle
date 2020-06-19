package model;

import java.util.List;

import dao.OrderRecordDao;
import dto.OrderRecordDto;
import dto.QueryDto;

public class GetOrderRecordListLogic {
	//レコード取得
	
	
	
	public List<OrderRecordDto> execute(QueryDto queryDto) {
		OrderRecordDao dao = new OrderRecordDao();
		List<OrderRecordDto> orderRecordList = dao.findAll(queryDto);
		return orderRecordList;
	}
	
	//カラム名取得
	public List<String> executeColumn() {
		OrderRecordDao dao = new OrderRecordDao();
		List<String> column = dao.findColumn();
		return column;
	}

}
