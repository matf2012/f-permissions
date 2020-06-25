# SQL 数据权限编辑工具

## 示例
### 1 
### 代码
	public static String sql_1= "select t1.col1,t2.col2 from table_1 t1 left join table_2 t2 on t1.id = t2.t1_id where t1.w_1 = '1'";
    
    public void test_1_1(){
        IPermissions permissions = new FPermissionsCore();

        PermissionsDTO dto = new PermissionsDTO();
        dto.setSql(sql_1);

        List<PermissionsWhereDTO> values = new ArrayList<>();

        values.add(genWHere("table_1","status","1"));
        values.add(genWHere("table_1","status","1","2"));
        values.add(genWHere("table_2","status","1","2"));

        dto.setValues(values);

        permissions.handle(dto);

    }
	
	private PermissionsWhereDTO genWHere(String table,String column,String ... values){

        PermissionsWhereDTO v1 = new PermissionsWhereDTO();
        v1.setTableName(table);
        v1.setColumn(column);
        if(values != null){
            List<String> v1Values = Arrays.asList(values);
            v1.setValues(v1Values);
            if(values.length > 1){
                v1.setOperator(PermissionsOperator.IN);
            }
        }
        return v1;
    }
	输出：
	
	处理前sql : 
		select t1.col1,t2.col2 from table_1 t1 left join table_2 t2 on t1.id = t2.t1_id where t1.w_1 = '1'
	处理后sql：
		SELECT t1.col1, t2.col2 FROM table_1 t1 LEFT JOIN table_2 t2 ON t1.id = t2.t1_id WHERE t1.w_1 = '1' AND t1.status = '1' AND t1.status IN ('1', '2') AND t2.status IN ('1', '2')

### 