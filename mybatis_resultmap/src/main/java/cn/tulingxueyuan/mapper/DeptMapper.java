package cn.tulingxueyuan.mapper;

import cn.tulingxueyuan.pojo.Dept;

public interface DeptMapper {
    Dept SelectDept(Integer id);
    //嵌套结果(异步查询)：一对多 查询部门及所有员工
    Dept SelectDeptAndEmps(Integer id);
}
