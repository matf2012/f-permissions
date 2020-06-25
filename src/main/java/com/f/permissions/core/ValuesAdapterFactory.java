package com.f.permissions.core;

import com.f.permissions.IValuesAdapter;
import com.f.permissions.entity.PermissionsWhereDTO;
import com.f.permissions.exception.PermissionsException;

/**
 * @Description:
 * @author: feng
 * @date: 2020-06-07
 */
class ValuesAdapterFactory {

    public static IValuesAdapter[] adapters = {new EqualsValuesAdapter(),new InValuesAdapter()};

    public static IValuesAdapter get(PermissionsWhereDTO value){
        if(value.getValues() == null || value.getValues().size() == 0){
            throw new PermissionsException("value is null ："+value);
        }
        switch (value.getOperator()){
            case IN:
                return adapters[1];
            case EQUALS:
                return adapters[0];
        }
        return adapters[0];
    }
}
