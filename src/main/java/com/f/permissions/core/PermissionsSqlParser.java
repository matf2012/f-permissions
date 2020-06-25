package com.f.permissions.core;

import com.f.permissions.IValuesAdapter;
import com.f.permissions.entity.PermissionsDTO;
import com.f.permissions.entity.PermissionsWhereDTO;
import com.f.permissions.entity.SqlParserDTO;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;

import java.util.ArrayList;
import java.util.List;

/**
 * sql解析处理类
 * @Description: 解析sql，处理where条件变化
 * @author: feng
 * @date: 2020-06-07
 */
@Slf4j
class PermissionsSqlParser {

    private PermissionsDTO permissions;

    private List<SqlParserDTO> selectList = new ArrayList<>();


    public PermissionsSqlParser(PermissionsDTO permissions){
        this.permissions = permissions;
    }

    public PermissionsSqlParser(String sql){
        PermissionsDTO permissions = new PermissionsDTO();
        permissions.setSql(sql);
        this.permissions = permissions;
    }


    /**
     * 处理sql片段
     * @param plainSelect
     */
    public void plainSelect(PlainSelect plainSelect){
        SqlParserDTO parserDTO = new SqlParserDTO();
        parserDTO.setSelect(plainSelect);
        selectList.add(parserDTO);
        List<Table> list = new ArrayList<>();
        list.addAll(fromItem(plainSelect.getFromItem()));
        list.addAll(joins(plainSelect.getJoins()));
        parserDTO.setTables(list);
    }

    /**
     * 读取joins的表信息
     * @param joins
     * @return
     */
    public List<Table> joins(List<Join> joins){

        List<Table> list = new ArrayList<>();
        if(joins != null && joins.size() > 0){
            for(Join join : joins){
                list.addAll(fromItem(join.getRightItem()));
            }
        }
        return list;
    }

    /**
     * 读取fromItem的表信息
     * @param fromItem
     * @return
     */
    public List<Table> fromItem(FromItem fromItem){
        List<Table> list = new ArrayList<>();
        if(fromItem != null){
            if(fromItem instanceof SubJoin){
                list.addAll(subJoin((SubJoin) fromItem));
            }else if(fromItem instanceof LateralSubSelect){
                log.debug("TODO LateralSubSelect : {}",fromItem);
            }else if(fromItem instanceof SubSelect){
                log.debug("TODO SubSelect : {}",fromItem);
            }else if(fromItem instanceof ValuesList){
                log.debug("TODO ValuesList : {}",fromItem);
            }else if(fromItem instanceof TableFunction){
                log.debug("TODO TableFunction : {}",fromItem);
            }else if(fromItem instanceof Table){
                list.add((Table) fromItem);
            }
        }
        return list;
    }

    /**
     * 读取subJoin的表信息
     * @param subJoin
     * @return
     */
    public List<Table> subJoin(SubJoin subJoin){
        List<Table> list = new ArrayList<>();
        FromItem left = subJoin.getLeft();
        list.addAll(fromItem(left));

        Join join = subJoin.getJoin();
        FromItem right = join.getRightItem();
        list.addAll(fromItem(right));
        return list;
    }

    public void withItem(WithItem withItem){
        log.debug("TODO whth item : {}",withItem.toString());
    }

    /**
     * 处理sql
     * @param body
     */
    public void selectBody(SelectBody body){
        if(body instanceof SetOperationList){
            SetOperationList sbody = (SetOperationList )body;
            List<SelectBody> selectList = sbody.getSelects();
            log.debug("SetOperationList ! list size : {}",selectList.size());
            for(SelectBody item : selectList){
                selectBody(item);
            }
        }else if(body instanceof PlainSelect){

            log.debug("PlainSelect ! ");
            plainSelect((PlainSelect)body);
        }else if(body instanceof WithItem){

            log.debug("WithItem ! ");
            withItem((WithItem)body);
        }

    }

    /**
     * 初始化sql
     * @return
     */
    public Statement init(){
        try {
            this.selectList().clear();
            Statement stmt =  CCJSqlParserUtil.parse(permissions.getOriSql());
            if(stmt instanceof Select){
                Select select = (Select)stmt;
                selectBody(select.getSelectBody());
            }
            return stmt;
        } catch (JSQLParserException e) {
            log.error("permissions sql handle error !",e);
        }
        return null;
    }

    public List<SqlParserDTO> selectList(){
        return selectList;
    }

    /**
     * 处理sql
     * @return
     */
    public String handle(){
        Statement stmt =  this.init();
        String sql = permissions.getOriSql();
        if(stmt != null && permissions.getValues() != null){
            // 循环处理条件
            for(PermissionsWhereDTO value : permissions.getValues()){
                convert(value);
            }
            sql = stmt.toString();
            permissions.setHandleFlag(true);
        }
        return sql;
    }

    protected void convert(PermissionsWhereDTO value){
        if(value != null){
            for(SqlParserDTO parser : selectList){
                Table table = getTable(value,parser);
                if(table != null){
                    IValuesAdapter adapter = ValuesAdapterFactory.get(value);
                    if(adapter != null){
                        adapter.convert(value,table,parser);
                    }
                }
            }
        }
    }

    protected Table getTable(PermissionsWhereDTO value,SqlParserDTO parser){
        if(value.getTableName() != null && parser.getTables() != null){
            for( Table table : parser.getTables()){
                if(value.getTableName().equalsIgnoreCase(table.getName().replaceAll("`",""))){
                    return table;
                }
            }
        }
        return null;
    }
}
