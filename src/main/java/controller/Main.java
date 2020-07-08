package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
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
		// 出力条件を取得
		request.setCharacterEncoding("UTF-8");
		String query_order_id = request.getParameter("query_order_id");
		String query_order_date = request.getParameter("query_order_date");
		String query_age_range = request.getParameter("query_age_range");
		QueryDto queryDto = new QueryDto(query_order_id, query_order_date, query_age_range);

		// Excelの出力
		GetOrderRecordListLogic getOrderRecordListLogic = new GetOrderRecordListLogic();
		Workbook book = getOrderRecordListLogic.outputExcel(queryDto);

		// ダウンロードさせる
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

		return;

	}

}
