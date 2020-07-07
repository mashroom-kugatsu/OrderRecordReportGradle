package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	private static final File DOWNLOAD_DIR = new File("D:\\downloadtest");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータの取得
		// TODO: ありなしの判定をつくる
		// FIXME:getParemeterValues調べる

		request.setCharacterEncoding("UTF-8");
		String query_order_id = request.getParameter("query_order_id");
		String query_order_date = request.getParameter("query_order_date");
		String query_age_range = request.getParameter("query_age_range");

		// リストの取得

		// if(query_order_id == null)

		// TODO:Logic.javaに引越しする
		QueryDto queryDto = new QueryDto(query_order_id, query_order_date, query_age_range);
		GetOrderRecordListLogic getOrderRecordListLogic = new GetOrderRecordListLogic();
		List<OrderRecordDto> orderRecordList = getOrderRecordListLogic.execute(queryDto);
		List<String> columnNameList = getOrderRecordListLogic.executeCulumn();

		// リストからExcelに出力するデータをセット
		// ワークブック作成
		Workbook book = new XSSFWorkbook();

		// シート作成
		Sheet sheet = book.createSheet();

		//1行目にカラム名をセット
		Row row = sheet.createRow(0);

		row.createCell(0).setCellValue(columnNameList.get(0));
		row.createCell(1).setCellValue(columnNameList.get(1));
		row.createCell(2).setCellValue(columnNameList.get(2));
		row.createCell(3).setCellValue(columnNameList.get(3));
		row.createCell(4).setCellValue(columnNameList.get(4));
		row.createCell(5).setCellValue(columnNameList.get(5));
		row.createCell(6).setCellValue(columnNameList.get(6));
		row.createCell(7).setCellValue(columnNameList.get(7));
		row.createCell(8).setCellValue(columnNameList.get(8));
		row.createCell(9).setCellValue(columnNameList.get(9));

		//レコードをセット
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

		// TODO: ダウンロード機能を実装する
		// ファイルに保存
		String fileName = "OrderRecordReport.xlsx";
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
		String nameDate = dateFormat.format(date);

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + nameDate + fileName);
		response.setHeader("Content-Description", "file download");
		response.setContentType("application/vnd.ms-excel");

		book.write(response.getOutputStream());
		book.close();

		System.out.println("Excel出力完了");

	}

}
