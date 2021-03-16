package cn.tulingxueyuan.tests;



import cn.tulingxueyuan.mapper.EmpMapper;
import cn.tulingxueyuan.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MybatisTest {
    SqlSessionFactory sqlSessionFactory;
    @Before
    public void before(){
        //从XML中构建SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    //基于mapper接口的方式去执行SQL
    @Test
    public void test01(){
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            QueryEmpDTO dto=new QueryEmpDTO();
            dto.setId(1);
            List<Emp> emps = mapper.QueryEmp(dto);
            System.out.println(emps);
        }
    }

    @Test
    public void test02(){
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.QueryEmp2("%徐%");
            System.out.println(emps);
        }
    }
//循环逐条插入  412ms
//使用ExecutorType.BATCH进行批量查询  348ms
//mysql的批量SQL插入  283ms
    @Test
    public void test03(){
        List<Emp> list=new ArrayList<>();
        for(int i=0;i<1000;i++){
            list.add(new Emp(null,"测试"+i, LocalDate.now(),2));
        }
        long begin = System.currentTimeMillis();
        //try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
                //System.out.println(mapper.insert(emp));
                System.out.println(mapper.insertBatch(list));
            session.commit();
        }
        long end=System.currentTimeMillis();
        System.out.println(end-begin+"ms");
    }

}
