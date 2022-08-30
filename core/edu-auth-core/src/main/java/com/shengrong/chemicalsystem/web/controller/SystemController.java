package com.shengrong.chemicalsystem.web.controller;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.dao.TestDao;
import com.shengrong.chemicalsystem.dao.TestQuery;
import com.shengrong.chemicalsystem.service.TestTableService;
import com.shengrong.chemicalsystem.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@RestController
public class SystemController {

    @Resource
    private TestDao testDao;

    @RequestMapping(method = RequestMethod.GET, value = CommonConstant.SYSTEM_PUBLIC_KEY)
    public Object getPublicKey(){
        log.info("sqlSession = {}", sqlSession.getClass());
        Map<String, String> m = new Hashtable<>(2);
        m.put("id", UUID.randomUUID().toString());
        m.put("name", "zhaosi2");
        //org/flowable/variable/service/db/mapping/entity/HistoricVariableInstance.xml


//        testDao.insertTestTable(UUID.randomUUID().toString(), "222222");
//        sqlSession.insert("insertTestTable", m);

//        final List<Object> query = sqlSession.selectList("query");
//        System.out.println("=====_____" + query);

        TestQuery q = new TestQuery();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        q.setIds(list);


        final List<String> select = testDao.query(q);
        System.out.println("=====" + select);
//        RSAPublicKey publicKey = RSAUtils.getPublicKey();
//        byte[] keyEncoded = publicKey.getEncoded();
//        String pub = Base64.encodeBase64String(keyEncoded);
        return ResponseUtils.getDataResponse(select);
    }

    @Resource
    private TestTableService testTableService;

    @RequestMapping(method = RequestMethod.POST, value = CommonConstant.SYSTEM_PUBLIC_KEY)
    public Object getPublicKey2(int index, String method){
        if ("add".equals(method)) {
            testTableService.add(index);
        }else if ("add0".equals(method)) {
            testTableService.add0(index);
        } else {
            testTableService.add1(index);
        }
        return ResponseUtils.getDataResponse("select");
    }



    @Resource
    private SqlSession sqlSession;

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public Object test(){
        log.info("sqlSession = {}", sqlSession.getClass());

        return "SUCCESS";
    }

}
