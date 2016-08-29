package com.snowinpluto.demo.mybatis.dialect;

/**
 * Oracle数据库分页Sql语句处理
 * ClassName : OracleDialect
 * Date : Jul 5, 2014
 * @author ZhongKeqiang 
 * @since JDK 1.7 
 */
public class OracleDialect extends Dialect {
	
	public boolean supportsLimitOffset() {

		return true;
	}

	public boolean supportsLimit() {

		return true;
	}

	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {
		StringBuilder pageSql = new StringBuilder(100);
		String beginrow = offsetPlaceholder;
	    String endrow = String.valueOf(offset + limit);
	    pageSql.append("select * from ( select temp.*, rownum row_id from ( ");  
	    pageSql.append(sql);  
	    pageSql.append(" ) temp where rownum <= ").append(endrow);  
	    pageSql.append(") where row_id > ").append(beginrow);  
	    return pageSql.toString();
	}
}
