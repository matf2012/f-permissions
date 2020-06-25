package com.f.permissions.entity;

import com.f.permissions.constants.PermissionsOperator;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: feng
 * @date: 2020-06-07
 */
@Data
public class PermissionsWhereDTO {

    private String tableName;

    private String column;

    private List<String> values;

    private PermissionsOperator operator = PermissionsOperator.EQUALS;

}
