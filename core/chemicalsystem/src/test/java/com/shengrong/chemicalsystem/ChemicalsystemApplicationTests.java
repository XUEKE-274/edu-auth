package com.shengrong.chemicalsystem;

import com.shengrong.chemicalsystem.dao.TotalDao;
import com.shengrong.chemicalsystem.model.entity.OrderEntity;
import com.shengrong.chemicalsystem.model.entity.TotalEntity;
import com.shengrong.chemicalsystem.service.OrderService;
import com.shengrong.chemicalsystem.utils.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Transactional
public class ChemicalsystemApplicationTests {

    public static void main(String[] args) throws Exception {
        String encoded = RSAUtils.encoded("admin");
        System.out.println(encoded);
//        "EAA78wDwjdH1mtuhpsxOek+23s06EaIAyEP2pSGWPGQXZd1tlZ7qnxiw3XJ6nqLmUSyMm7GCvA/mkMbJb6LFadgt/NYvJ6WV7sKGsxa+Yq8Gzc327hal+EbjXkhfSEH+96kubhm1DwAH+PtzkA7ZXUSy6RCZ4bUnQcD3EbA9PBM="
    }

    @Test
    public void contextLoads() {
    }

    @Autowired
    private OrderService orderService;


    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    TotalDao dao;

    @Test
    @Transactional
    public void event() throws Exception{
//        System.out.println(bCryptPasswordEncoder.encode("admin"));
        TotalEntity entity = new TotalEntity();
        entity.setId("111");
        entity.setVersion("v1");
        entity.setCreator("Test>>");
        entity.setResult("12312312");
        dao.insert(entity);

    }

}
