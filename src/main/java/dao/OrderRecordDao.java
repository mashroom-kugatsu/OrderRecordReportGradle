package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.OrderRecordDto;
import dto.QueryDto;

public class OrderRecordDao {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:postgresql://localhost/postgres";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "postgres";
	public List<String> columnNameList = null;

	// レコードを取得してリストに格納するメソッド
	public List<OrderRecordDto> findAll(QueryDto queryDto) {
		List<OrderRecordDto> orderRecordList = new ArrayList<OrderRecordDto>();

		// データベース接続
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備

			String query_order_date = queryDto.getQuery_order_date();
			String query_order_id = queryDto.getQuery_order_id();
			String query_age_range = queryDto.getQuery_age_range();

			StringBuffer sql = new StringBuffer();

			sql.append("select \r\n" + "    orders.order_id --0\r\n" + "	,orders.order_time --1\r\n"
					+ "	,orders.shop_id --2\r\n" + "	,orders.order_amount --3\r\n"
					+ "	,customers.customer_id --4\r\n" + "	,customers.customer_name --5\r\n"
					+ "	,customers.customer_age --6\r\n" + "	,customers.customer_birthday --7\r\n"
					+ "	,customers.customer_gender --8 \r\n" + "	,customers.customer_location --9\r\n"
					+ "from orders\r\n" + "left join customers on orders.customer_id = customers.customer_id \r\n"
					+ "where\r\n" + "    order_time >= '2012-01-01 00:00:00' \r\n");

			// 注文日が選択された場合
			if (query_order_date != "") {
				sql.append(" and order_time >='" + query_order_date + " 00:00:00" + "'\r\n");
			}

			// 注文番号が選択された場合
			if (query_order_id != null) {
				if (query_order_id.equals("1")) {
					sql.append(" and order_id >=1 and order_id <= 10000 \r\n");
				} else if (query_order_id.equals("2")) {
					sql.append(" and order_id >=10001 and order_id <= 20000 \r\n");
				} else if (query_order_id.equals("3")) {
					sql.append(" and order_id >=20001 and order_id <= 30000 \r\n");
				} else if (query_order_id.equals("4")) {
					sql.append(" and order_id >=30001 and order_id <= 40000 \r\n");
				} else if (query_order_id.equals("5")) {
					sql.append(" and order_id >=40001 and order_id <= 50000 \r\n");
				}
			}

			// 年代が選択された場合
			if (query_age_range != null) {
				if (query_age_range.equals("1"))
					sql.append(" and (customer_age/10)*10 = 10 \r\n");
				if (query_age_range.equals("2"))
					sql.append(" and (customer_age/10)*10 = 20 \r\n");
				if (query_age_range.equals("3"))
					sql.append(" and (customer_age/10)*10 = 30 \r\n");
				if (query_age_range.equals("4"))
					sql.append(" and (customer_age/10)*10 = 40 \r\n");
				if (query_age_range.equals("5"))
					sql.append(" and (customer_age/10)*10 = 50 \r\n");
			} else {
				sql.append("");
			}

			sql.append("order by order_id asc, order_time asc \r\n");

			// SQL文確認
			System.out.println("SQL文表示");
			System.out.println(sql);

			PreparedStatement pStmt = conn.prepareStatement(new String(sql));

			// SELECTの実行
			ResultSet rs = pStmt.executeQuery();

			// SELECTの結果をArrayListに格納
			while (rs.next()) {

				int order_id = rs.getInt("order_id");
				String order_time = rs.getString("order_time");
				int shop_id = rs.getInt("shop_id");
				int order_amount = rs.getInt("order_amount");
				int customer_id = rs.getInt("customer_id");
				String customer_name = rs.getString("customer_name");
				int customer_age = rs.getInt("customer_age");
				String customer_birthday = rs.getString("customer_birthday");
				String customer_gender = rs.getString("customer_gender");
				String customer_location = rs.getString("customer_location");

				OrderRecordDto dto = new OrderRecordDto(order_id, order_time, shop_id, order_amount, customer_id,
						customer_name, customer_age, customer_birthday, customer_gender, customer_location);

				orderRecordList.add(dto);
			}

			// カラム名の取得して、ArrayListに格納
			ResultSetMetaData rsmd = rs.getMetaData();

			columnNameList = new ArrayList<String>();

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.println(rsmd.getColumnName(i));
				columnNameList.add(rsmd.getColumnName(i));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return orderRecordList;
	}

	/**
	 * findAll実行後に使用してください
	 * 
	 * @return
	 */

	public List<String> getColumnName() {
		return columnNameList;
	}
}