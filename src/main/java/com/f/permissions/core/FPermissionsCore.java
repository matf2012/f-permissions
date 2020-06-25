package com.f.permissions.core;

import com.f.permissions.IPermissions;
import com.f.permissions.entity.PermissionsDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据权限核心处理业务类
 * @Description: 根据 where条件，处理sql。如果处理成功，将值handleFlag状态为true
 * @author: feng
 * @date: 2020-06-07
 */
@Slf4j
public class FPermissionsCore implements IPermissions {

    /**
     * 处理sql
     * @param permissions
     */
    @Override
    public void handle(PermissionsDTO permissions) {
        PermissionsSqlParser parser = new PermissionsSqlParser(permissions);

        // 记录原sql
        permissions.setOriSql(permissions.getSql());


        // 执行处理
        String sql = parser.handle();

        log.debug("old sql :");
        log.debug(permissions.getOriSql());
        log.debug("new sql :");
        log.debug(sql);

        // 赋值新sql
        permissions.setSql(sql);
    }
}
