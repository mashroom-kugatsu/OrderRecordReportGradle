package model;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiTest {

	public static void main(String[] args) {

		// ワークブック作成
		Workbook book = new XSSFWorkbook();

		// シート作成
		Sheet sheet = book.createSheet();

		// 行作成

		int rowStart = 1;
		int rowEnd = 5;
		int lastColumn = 9;

		for (int i = rowStart; i <= rowEnd; i++) {
			Row row = sheet.createRow(i);

			// セル作成
			for (int j = 0; j < lastColumn; j++) {
				Cell cell = row.createCell(j);
				// セルに値セット
				cell.setCellValue("test");
			}
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
