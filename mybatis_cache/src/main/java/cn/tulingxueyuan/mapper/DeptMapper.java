package cn.tulingxueyuan.mapper;

import cn.tulingxueyuan.pojo.Dept;
import cn.tulingxueyuan.pojo.Emp;

import java.util.List;

public interface DeptMapper {
    //查询部门
    List<Dept> selectDept(Dept dept);
}
