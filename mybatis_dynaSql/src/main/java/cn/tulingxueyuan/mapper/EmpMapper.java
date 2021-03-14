package cn.tulingxueyuan.mapper;


import cn.tulingxueyuan.pojo.Emp;
import cn.tulingxueyuan.pojo.QueryEmpDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
    List<Emp> QueryEmp(QueryEmpDTO dto);
    List<Emp> QueryEmp2(String username);
    Integer insert(Emp emp);
    Integer insertBatch(@Param("emps") List<Emp> emp);
}
