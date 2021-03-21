package cn.tulingxueyuan.tests;



import cn.tulingxueyuan.mapper.EmpMapper;
import cn.tulingxueyuan.pojo.Emp;
import com.github.pagehelper.PageHelper;
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



    @Test
    public void test03(){
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            PageHelper.startPage(1,5);
            List<Emp> emps = mapper.queryEmp();
            System.out.println(emps);
        }

    }

}
