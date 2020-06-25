package com.f.permissions.core;

import com.f.permissions.IValuesAdapter;
import com.f.permissions.entity.SqlParserDTO;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.statement.select.PlainSelect;

/**
 * @Description: 动态添加条件
 * @author: feng
 * @date: 2020-06-07
 */
abstract class AbsValuesAdapter implements IValuesAdapter {

    /**
     * 添加条件
     * where ： 1.已有条件时， 添加 and 条件  ， 2. 没有条件时 ，直接添加条件
     * @param expression 条件表达式
     * @param parser dto
     */
    protected void add(Expression expression, SqlParserDTO parser){
        PlainSelect select = parser.getSelect();
        // 判断是否已有条件语句
        if(select.getWhere() != null){
            AndExpression and = new AndExpression(select.getWhere(),expression);
            select.setWhere(and);
        }else{
            select.setWhere(expression);
        }
    }
}
