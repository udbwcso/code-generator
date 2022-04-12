package com.database.util;


import com.config.Database;
import com.config.Entity;
import com.database.util.TableUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class EntityGenerator {

    public static Map<String, Object> getTableInfo(String catalog, String schemaPattern,
                                                   String tableName, String[] types, Database database) throws Exception {

        List<Map<String, Object>> tableList = TableUtil.getTables(catalog, schemaPattern, tableName, types, database.getProperties());
        Map<String, Object> table = tableList.get(0);
        List<String> primaryKeyList = TableUtil.getPrimaryKeys(catalog, schemaPattern, tableName, database.getProperties());
        log.info("database table:" + tableName);
        List<Map<String, Object>> columnList = TableUtil.getColumns(catalog, schemaPattern, tableName, null, database.getProperties());

        for (Map<String, Object> column : columnList) {
            String typeName = String.valueOf(column.get("TYPE_NAME"));
            column.put("javaType", database.getJavaType().get(typeName.replaceAll(" ", "_")));
            column.put("jdbcType", database.getJdbcType().get(typeName));
            String columnName = String.valueOf((column.get("COLUMN_NAME")));
            log.info(columnName + "---" + typeName);
            for (int i = 0; i < primaryKeyList.size(); i++) {
                if (columnName.equals(primaryKeyList.get(i))) {
                    column.put("isPrimaryKey", true);
                }
            }
            log.info("column:" + columnName);
            String field = camelCase(columnName);
            column.put("field", field);
            column.put("firstUpperCaseField", uppercase(field, 0, 1));
        }
        table.put("columnList", columnList);
        return table;
    }

    private static String camelCase(String string) {
        if (!string.contains("_")) {
            return string;
        }
        String[] words = string.split("_");
        StringBuilder builder = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) {
            builder.append(uppercase(words[i], 0, 1));
        }
        return builder.toString();
    }

    public static String uppercase(String string, int beginIndex, int endIndex) {
        String uppercaseStr = string.substring(beginIndex, endIndex);
        return string.substring(0, beginIndex) + uppercaseStr.toUpperCase() + string.substring(endIndex);
    }

    public static String lowercase(String string, int beginIndex, int endIndex) {
        String lowercaseStr = string.substring(beginIndex, endIndex);
        return string.substring(0, beginIndex) + lowercaseStr.toLowerCase() + string.substring(endIndex);
    }

}
