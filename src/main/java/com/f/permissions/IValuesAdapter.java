package com.f.permissions;

import com.f.permissions.entity.PermissionsWhereDTO;
import com.f.permissions.entity.SqlParserDTO;
import net.sf.jsqlparser.schema.Table;

public interface IValuesAdapter {

    void convert(PermissionsWhereDTO whereDTO, Table table, SqlParserDTO parser);

}
