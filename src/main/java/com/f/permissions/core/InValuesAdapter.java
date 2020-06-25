package com.f.permissions.core;

import com.f.permissions.entity.PermissionsWhereDTO;
import com.f.permissions.entity.SqlParserDTO;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

import java.util.ArrayList;

/**
 * 条件处理 ： 包含 IN
 * @Description: 处理where 条件是 包含情况
 * @author: feng
 * @date: 2020-06-07
 */
class InValuesAdapter extends AbsValuesAdapter{

    /**
     *  处理
     * @param whereDTO where 条件dto
     * @param table 表
     * @param parser
     */
    @Override
    public void convert(PermissionsWhereDTO whereDTO, Table table, SqlParserDTO parser) {


        // 包含条件
        InExpression addWhere = new InExpression();
        Column addColumn = new Column();
        addColumn.setColumnName(whereDTO.getColumn());
        if(table != null){
            addColumn.setTable(table);
        }
        addWhere.setLeftExpression(addColumn);
        ExpressionList list = new ExpressionList();
        list.setExpressions(new ArrayList<>());
        for(String value : whereDTO.getValues()){
            list.getExpressions().add(new StringValue(value));
        }
        addWhere.setRightItemsList(list);

        add(addWhere,parser);
    }
}
