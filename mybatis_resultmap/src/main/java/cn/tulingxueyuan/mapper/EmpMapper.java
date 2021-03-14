package cn.tulingxueyuan.mapper;


import cn.tulingxueyuan.pojo.Emp;

import java.util.List;
import java.util.Map;

public interface EmpMapper {

    Map<String,Object> QueryEmp(Integer id);
    //嵌套结果  多对一
    Emp QueryEmp2(Integer id);
    //嵌套查询(分步查询)  多对一
    Emp QueryEmp3(Integer id);
}
