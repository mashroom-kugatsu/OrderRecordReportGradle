package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dto.OrderRecordDto;
import model.GetOrderRecordListLogic;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リストの取得

		GetOrderRecordListLogic getOrderRecordListLogic = new GetOrderRecordListLogic();
		List<OrderRecordDto> orderRecordList = getOrderRecordListLogic.execute();

		// リストにデータが入っているか確認

		System.out.println("リストにデータが入っているか確認");
		for (OrderRecordDto dto : orderRecordList) {
			System.out.print(dto.getOrder_id() + ",");
			System.out.print(dto.getOrder_time() + ",");
			System.out.print(dto.getCustomer_name() + ",");
			System.out.print(dto.getCustomer_age() + ",");
			System.out.print(dto.getCustomer_gender() + ",");
			System.out.println("");
		}

		/*
		 * // リストからExcelに出力するデータをセット
		 * 
		 * // ワークブック作成 Workbook book = new XSSFWorkbook();
		 * 
		 * // シート作成 Sheet sheet = book.createSheet();
		 * 
		 * // 行作成
		 * 
		 * int rowStart = 1; int rowEnd = 5; int lastColumn = 9;
		 * 
		 * for (int i = rowStart; i <= rowEnd; i++) { Row row = sheet.createRow(i);
		 * 
		 * // セル作成 for (int j = 0; j < lastColumn; j++) { Cell cell = row.createCell(j);
		 * // セルに値セット cell.setCellValue("test"); } }
		 */

		// リストからExcelに出力するデータをセット

		// ワークブック作成
		Workbook book = new XSSFWorkbook();

		// シート作成
		Sheet sheet = book.createSheet();

		//int rowStart = 0;
		//int rowEnd = orderRecordList.size();
		// int lastColumn = 5;

		/*
		 * for (int i = rowStart; i <= rowEnd; i++) { Row row = sheet.createRow(i); //
		 * for (int j = 0; j < lastColumn; j++) {
		 * 
		 * for (int j = 0; j < orderRecordList.size(); j++) { Cell cell =
		 * row.createCell(j); cell.setCellValue(orderRecordList.get(i).getOrder_id()); }
		 * }
		 */
		
		for (int i = 1; i < orderRecordList.size(); i++) {
			Row row = sheet.createRow(i);
			OrderRecordDto dto = orderRecordList.get(i);

			// セルに値セット
			row.createCell(0).setCellValue(dto.getOrder_id());
			row.createCell(1).setCellValue(dto.getOrder_time());
			row.createCell(2).setCellValue(dto.getCustomer_name());
			row.createCell(3).setCellValue(dto.getCustomer_age());
			row.createCell(4).setCellValue(dto.getCustomer_gender());
		}


		// ファイルに保存
		String filename = "C:\\Users\\S.Matsukawa\\Desktop\\Java\\デリバリーレポート再現練習用\\workbook.xlsx";

		try (FileOutputStream out = new FileOutputStream(filename);) {
			book.write(out);
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
