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


    //基于statementId的方式去执行SQL
    @Test
    public void test01() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Emp emp = (Emp) session.selectOne("cn.tulingxueyuan.pojo.EmpMapper.selectEmp", 1);
            System.out.println(emp);
        }
    }
    //基于接口绑定的方式
//    1.新建数据访问层的接口：POJOMapper
//    2.添加mapper中对应的操作的方法
//        1.方法名要和mapper中对应的操作节点的id要一致
//        2.返回类型要和mapper中对应的操作的节点的resultType要一致
//        3.mapper中对应的操作的节点的参数必须要在方法的参数中声明
//    3.Mapper.xml中的namespace必须要和接口的完整限定名一致
//    4.修改mybatis全局配置文件中的mappers，采用接口绑定的方式
//    5.一定要将mapper.xml和接口放到同一级目录中，只需要在resources新建和接口同样结构的文件夹就行，生成就会合并
    @Test
    public void test02() throws IOException{
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmp(1);
            System.out.println(emp);
        }
    }
    //基于注解的方式
    //注解可以和xml混用，但是不能同时存在方法对应的xml的id
    @Test
    public void test03() throws IOException{
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmp(1);
            System.out.println(emp);
        }
    }
}
