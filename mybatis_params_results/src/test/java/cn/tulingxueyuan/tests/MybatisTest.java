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
import java.util.List;
import java.util.Map;

//mybatis搭建步骤
//1.添加pom依赖（mybatis的核心jar包和数据库版本对应版本的驱动jar包）
//2.新建数据库和表
//3.添加mybatis全局配置文件
//4.修改全局配置文件中的数据源配置信息
//5.添加数据库表对应的POJO对象（实体类）
//6.添加对应的PojoMapper.xml（维护所有的sql）
//    修改namespace:如果是statementId没有特殊要求
//                如果是接口绑定形式必须等于接口的完整限定名
//    修改对应的id（唯一）resultType 对应返回的类型 如果是POJO需要制定完整限定名
//7.修改mybatis全局配置文件：修改Mapper
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
            List<Emp> emp = mapper.selectEmp(3);
            System.out.println(emp);
        }
    }

}
