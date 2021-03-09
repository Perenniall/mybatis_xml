package cn.tulingxueyuan.mapper;


import cn.tulingxueyuan.pojo.Emp;

import java.util.List;
import java.util.Map;

public interface EmpMapper {

    //根据id查询Emp实体
    //@Select("select * from emp where id=#{id}")
    List<Emp> selectEmp(Integer id);
}
