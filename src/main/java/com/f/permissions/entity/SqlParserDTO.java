package com.f.permissions.entity;

import lombok.Data;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;

import java.util.List;

/**
 * @Description:
 * @author: feng
 * @date: 2020-06-07
 */
@Data
public class SqlParserDTO {

    private String sql;

    private PlainSelect select;

    private List<Table> tables ;
}
