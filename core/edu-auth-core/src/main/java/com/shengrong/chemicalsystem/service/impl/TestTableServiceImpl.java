package com.shengrong.chemicalsystem.service.impl;

import com.shengrong.chemicalsystem.dao.TestDao;
import com.shengrong.chemicalsystem.dao.UserDao;
import com.shengrong.chemicalsystem.service.TestTableService;
import com.shengrong.chemicalsystem.utils.IdUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.SimpleTransactionStatus;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author xueke274
 * @version 1.0
 * @date 2022/8/8 18:50
 * @description
 */
@Service
public class TestTableServiceImpl implements TestTableService {


    @Resource
    private TestDao dao;


    @Resource
    private PlatformTransactionManager platformTransactionManager;


    @Override
    public void add(int a) {
        DefaultTransactionDefinition dtf = new DefaultTransactionDefinition();
        dtf.setName("txName");
        dtf.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = platformTransactionManager.getTransaction(dtf);
        try {
            dao.insertTestTable(IdUtils.getUUID(), "zhangsan");
            if (a == 0) {
                throw new RuntimeException("333");
            }
            dao.insertTestTable(IdUtils.getUUID(), "zhoasi");
            // platformTransactionManager.commit(status);
        }catch (Exception e) {
            platformTransactionManager.rollback(status);
        }
    }

    @Override
    public void add0(int a) {
        dao.insertTestTable(IdUtils.getUUID(), "zhangsan");
        if (a == 0) {
            throw new RuntimeException("333");
        }
        dao.insertTestTable(IdUtils.getUUID(), "zhoasi");
    }



    @Override
    public void add1(int a) {
        DefaultTransactionDefinition dtf = new DefaultTransactionDefinition();
        dtf.setName("txName");
        dtf.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = platformTransactionManager.getTransaction(dtf);
        try {
            dao.insertTestTable(IdUtils.getUUID(), "zhangsan");
            if (a == 0) {
                throw new RuntimeException("333");
            }
            dao.insertTestTable(IdUtils.getUUID(), "zhoasi");

        }catch (Exception e) {
            status.setRollbackOnly();
        }
        platformTransactionManager.commit(status);


    }


    public static void main(String[] args ) throws Exception {
        String url = "jdbc:mysql://1.14.108.113:3306/kms";
        String user = "root";
        String password = "admin";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);

        conn.setAutoCommit(false);
        String sql = conn.nativeSQL("insert into test_transactions values ('xx1',  'zhangsan')");
        PreparedStatement prepareStatement = conn.prepareStatement(sql);


        prepareStatement.execute();

        conn.commit();

        sql = conn.nativeSQL("insert into test_transactions values ('xx2',  'zhangsan')");
        PreparedStatement prepareStatement2 = conn.prepareStatement(sql);
        prepareStatement2.execute();
        conn.commit();

    }




}
