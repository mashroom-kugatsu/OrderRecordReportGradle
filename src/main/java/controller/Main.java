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
import dto.QueryDto;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String query_order_id = request.getParameter("query_order_id");
		String query_order_date = request.getParameter("query_order_date");
		String query_age_range = request.getParameter("query_age_range");

		// リストの取得
		
		//if(query_order_id == null)
		
		QueryDto queryDto = new QueryDto(query_order_id, query_order_date, query_age_range);
		GetOrderRecordListLogic getOrderRecordListLogic = new GetOrderRecordListLogic();
		List<OrderRecordDto> orderRecordList = getOrderRecordListLogic.execute(queryDto);
		List<String> column = getOrderRecordListLogic.executeColumn();

		// リストにデータが入っているか確認

		System.out.println("--リストにデータが入っているか確認--");
		for (OrderRecordDto dto : orderRecordList) {
			System.out.print(dto.getOrder_id() + ",");
			System.out.print(dto.getOrder_time() + ",");
			System.out.print(dto.getCustomer_name() + ",");
			System.out.print(dto.getCustomer_age() + ",");
			System.out.print(dto.getCustomer_gender() + ",");
			System.out.println("");
		}

		// リストからExcelに出力するデータをセット

		// ワークブック作成
		Workbook book = new XSSFWorkbook();

		// シート作成
		Sheet sheet = book.createSheet();
		
		
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

		// ファイルに保存
		String filename = "C:\\Users\\S.Matsukawa\\Desktop\\Java\\デリバリーレポート再現練習用\\workbook.xlsx";

		try (FileOutputStream out = new FileOutputStream(filename);) {
			book.write(out);
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Excel出力完了");

	}

}
