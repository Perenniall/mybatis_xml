package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.mapper.EmpMapper;
import cn.tulingxueyuan.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    @Test
    public void test01() throws IOException {
        //根据全局配置文件将xml构建成SqlsessionFactory
        String config="mybatis-config.xml";
        //将xml构建成输入流
        InputStream inputStream= Resources.getResourceAsStream(config);
        //构建SqlsessionFactory：将全局配置文件和所有的mapper全部加载到Configuration
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //SqlSession负责执行具体的数据库操作
        try(SqlSession sqlSession=sqlSessionFactory.openSession()){
            //Mybatis在getMapper就会创建jdk动态代理
            EmpMapper mapper=sqlSession.getMapper(EmpMapper.class);
            Emp emp=mapper.selectEmp(1);
            System.out.println(emp);

        }
    }
}
