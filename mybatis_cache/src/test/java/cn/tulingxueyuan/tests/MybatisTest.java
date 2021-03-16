package cn.tulingxueyuan.tests;


import cn.tulingxueyuan.mapper.DeptMapper;
import cn.tulingxueyuan.pojo.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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

    //一级缓存
    //1.默认开启,也可以关闭一级缓存
    //2.作用域：基于SqlSession（默认），一次数据库操作会话
    //3.缓存默认实现类PerpetualCache，使用map进行存储
    //4.查询完就会存储
    //失效情况
    //1.不同的SqlSession会使一级缓存失效
    //2.同一个SqlSession，但是查询语句不一样
    //3.同一个SqlSession，查询语句一样，期间执行增删改操作
    //4.同一个SqlSession，查询语句一样，执行手动清除缓存
    @Test
    public void test01(){
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DeptMapper mapper = session.getMapper(DeptMapper.class);
            List<Dept> depts =mapper.selectDept(null);
            System.out.println(depts);
        }
    }

    /*
    * 二级缓存
    *1.默认开启，没有实现
    *2.作用域：基于全局范围，应用级别
    *3.缓存默认实现类PerpetualCache,使用map进行存储,但是二级缓存根据不同的mapper命名空间
    * 多包一层map  key：mapper命名空间 value:erpetualCache.map
    * key==>sqlid+sql+environment默认id
    * 4.事务提交时存储
    * 5.先从二级缓存中获取，再从一级缓存中获取
    * 实现：
    * 1.开启二级缓存<setting name="cacheEnabled" value="true"/>
    * 2.在需要用到二级缓存的映射文件中加入<cache></cache>，基于mapper映射文件来实现缓存,基于mapper
    * 映射文件的命名空间来存储
    * 3.在需要使用到二级缓存的javaBean中实现序列化接口
    * 配置成功就会出现缓存命中率  同一sqlid从缓存中拿出的次数/查询总次数
    * 失效：
    * 1.同一个命名空间进行增删改操作，二级缓存失效
    * 但是如果不想失效：可以将flushCache设置为false，默认为true
    * 当执行该sql就会清空二级缓存，false就不会清空二级缓存
    *但是要慎重设置,可能会造成数据脏读现象,除非保证数据永远不会增删改
    * 2.让查询不缓存数据到二级缓存中usecache="false"
    * 3.如果希望其他mapper映射文件的命名空间执行了增删改清空另外的命名空间就可以设置
    * <cache-ref namespace="cn.tulingxueyuan.mapper.DeptMapper"/>
    * */
    @Test
    public void test02(){
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DeptMapper mapper = session.getMapper(DeptMapper.class);
            List<Dept> depts =mapper.selectDept(null);
            System.out.println(depts);
        }
    }

}
