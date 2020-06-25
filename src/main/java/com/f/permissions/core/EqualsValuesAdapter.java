package com.f.permissions.core;

import com.f.permissions.entity.PermissionsWhereDTO;
import com.f.permissions.entity.SqlParserDTO;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

/**
 * 条件处理 ： 相等 =
 * @Description: 处理where 条件是 等号情况
 * @author: feng
 * @date: 2020-06-07
 */
class EqualsValuesAdapter extends AbsValuesAdapter {
    @Override
    public void convert(PermissionsWhereDTO whereDTO, Table table, SqlParserDTO parser) {


        // 相等条件
        EqualsTo addWhere = new EqualsTo();
        Column addColumn = new Column();
        addColumn.setColumnName(whereDTO.getColumn());
        if(table != null){
            addColumn.setTable(table);
        }
        addWhere.setLeftExpression(addColumn);

        // 值，取第一个
        addWhere.setRightExpression(new StringValue(whereDTO.getValues().get(0)));

        add(addWhere,parser);
    }
}
