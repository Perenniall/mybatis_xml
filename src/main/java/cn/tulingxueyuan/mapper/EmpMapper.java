package cn.tulingxueyuan.mapper;

import cn.tulingxueyuan.pojo.Emp;

public interface EmpMapper {

    //根据id查询Emp实体
    //@Select("select * from emp where id=#{id}")
    Emp selectEmp(Integer id);
    //插入
    Integer insertEmp(Emp emp);
    //更新
    Integer updateEmp(Emp emp);
    //删除
    Integer deleteEmp(Integer id);
}
