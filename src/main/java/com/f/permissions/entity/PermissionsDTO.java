package com.f.permissions.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: feng
 * @date: 2020-06-07
 */
@Data
public class PermissionsDTO {

    private String sql;

    private String oriSql;

    private boolean handleFlag;

    private List<PermissionsWhereDTO> values;
}
