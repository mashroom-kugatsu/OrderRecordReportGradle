package model;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.OrderRecordDao;
import dto.OrderRecordDto;
import dto.QueryDto;

public class GetOrderRecordListLogic {

	OrderRecordDao dao = new OrderRecordDao();

	// レコード取得
	public List<OrderRecordDto> execute(QueryDto queryDto) {
		List<OrderRecordDto> orderRecordList = dao.getOrderRecordList(queryDto);
		return orderRecordList;
	}

	//Excel書きだし
	public Workbook outputExcel(QueryDto queryDto) {

		List<OrderRecordDto> orderRecordList = execute(queryDto);
		List<String> column = executeCulumn();

		Workbook book = new XSSFWorkbook();

		// シート作成
		Sheet sheet = book.createSheet();

		// 1行目にカラム名をセット
		Row row = sheet.createRow(0);

		row.createCell(0).setCellValue(column.get(0));
		row.createCell(1).setCellValue(column.get(1));
		row.createCell(2).setCellValue(column.get(2));
		row.createCell(3).setCellValue(column.get(3));
		row.createCell(4).setCellValue(column.get(4));
		row.createCell(5).setCellValue(column.get(5));
		row.createCell(6).setCellValue(column.get(6));
		row.createCell(7).setCellValue(column.get(7));
		row.createCell(8).setCellValue(column.get(8));
		row.createCell(9).setCellValue(column.get(9));

		// レコードをセット
		for (int i = 0; i < orderRecordList.size(); i++) {
			row = sheet.createRow(i + 1);

			OrderRecordDto dto = orderRecordList.get(i);

			// セルに値セット
			row.createCell(0).setCellValue(dto.getOrder_id());
			row.createCell(1).setCellValue(dto.getOrder_time());
			row.createCell(2).setCellValue(dto.getShop_id());
			row.createCell(3).setCellValue(dto.getOrder_amount());
			row.createCell(4).setCellValue(dto.getCustomer_id());
			row.createCell(5).setCellValue(dto.getCustomer_name());
			row.createCell(6).setCellValue(dto.getCustomer_age());
			row.createCell(7).setCellValue(dto.getCustomer_birthday());
			row.createCell(8).setCellValue(dto.getCustomer_gender());
			row.createCell(9).setCellValue(dto.getCustomer_location());
		}
		return book;
	}

	// カラム名取得
	public List<String> executeCulumn() {
		List<String> columnNameList = dao.getColumnName();
		return columnNameList;
	}
}
