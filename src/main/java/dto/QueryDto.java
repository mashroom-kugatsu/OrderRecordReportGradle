package dto;

public class QueryDto {

	String query_order_id;
	String query_order_date;
	String query_age_range;

	public QueryDto(String query_order_id, String query_order_date, String query_age_range) {
		this.query_order_id = query_order_id;
		this.query_order_date = query_order_date;
		this.query_age_range = query_age_range;
	}

	public String getQuery_order_id() {
		return query_order_id;
	}

	public void setQuery_order_id(String query_order_id) {
		this.query_order_id = query_order_id;
	}

	public String getQuery_order_date() {
		return query_order_date;
	}

	public void setQuery_order_date(String query_order_date) {
		this.query_order_date = query_order_date;
	}

	public String getQuery_age_range() {
		return query_age_range;
	}

	public void setQuery_age_range(String query_age_range) {
		this.query_age_range = query_age_range;
	}

}
