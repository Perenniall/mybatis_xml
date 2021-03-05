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

public class MybatisCRUD {
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
    //查询
    public void select(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.selectEmp(1);
        System.out.println(emp);
    }
    @Test
    //添加
    public void insert(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp=new Emp();
        emp.setUsername("徐庶");
        try{
            Integer result = mapper.insertEmp(emp);
            sqlSession.commit();
            System.out.println(result);
        }catch (Exception ex){
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }

    }
    //更新
    @Test
    public void update(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp=new Emp();
        emp.setId(3);
        emp.setUsername("曹操");
        try{
            Integer result = mapper.updateEmp(emp);
            sqlSession.commit();
            System.out.println(result);
        }catch (Exception ex){
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }
    //删除
    @Test
    public void deleteEmp(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        try{
            Integer result = mapper.deleteEmp(4);
            sqlSession.commit();
            System.out.println(result);
        }catch (Exception ex){
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }
}
