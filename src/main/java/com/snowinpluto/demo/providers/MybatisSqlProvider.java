package com.snowinpluto.demo.providers;

import java.util.List;
import java.util.Map;

public class MybatisSqlProvider {

    public static String findUserByNames(Map<String, Object> params) {
        List<String> names = (List<String>) params.get("names");

        StringBuilder sql = new StringBuilder();
        sql.append("select id, name ,age from user where name in (");
        for (int i = 0; i < names.size(); i++) {
            if (i > 0) {
                sql.append(",");
            }
            sql.append("'" + names.get(i) + "'");
        }
        sql.append(")");

        return sql.toString();
    }
}
