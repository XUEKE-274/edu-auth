package com.shengrong.chemicalsystem.controller;

import com.shengrong.chemicalsystem.controller.request.TotalRequest;
import com.shengrong.chemicalsystem.dao.TotalDao;
import com.shengrong.chemicalsystem.ecxeption.ChemicalException;
import com.shengrong.chemicalsystem.model.entity.TotalEntity;
import com.shengrong.chemicalsystem.utils.JsonUtils;
import com.shengrong.chemicalsystem.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static com.shengrong.chemicalsystem.ecxeption.ExceptionCodeEnum.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @author xueke274
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TotalController {


    @Resource
    private TotalDao dao;


    @RequestMapping(method = RequestMethod.POST, value = "/total")
    public Object add(@RequestBody TotalRequest request){

        this.checkGrade(request.getResult());





        TotalEntity entity = new TotalEntity();

        entity.setVersion(request.getVersion());
        entity.setCreator(request.getCreator());
        entity.setResult(JsonUtils.toString(request.getResult()));


        if (StringUtils.isEmpty(entity.getVersion())) {
            throw new ChemicalException(CS001);
        }
        if (StringUtils.isEmpty(entity.getCreator())) {
            throw new ChemicalException(CS001);
        }
        if (StringUtils.isEmpty(entity.getResult())) {
            throw new ChemicalException(CS001);
        }

        TotalEntity query = new TotalEntity();

        query.setVersion(entity.getVersion());
        query.setCreator(entity.getCreator());

        TotalEntity totalEntity = dao.selectOne(query);

        if (null != totalEntity) {
            throw new ChemicalException(CS007);
        }


        entity.setId(UUID.randomUUID().toString());
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        entity.setModifyTime(new Timestamp(System.currentTimeMillis()));

        dao.insert(entity);

        return ResponseUtils.getDefResponse();
    }

    private void checkGrade(List<TotalRequest.SingleData> result) {
        if (CollectionUtils.isEmpty(result)) {
            throw new ChemicalException(CS001);
        }
        for (TotalRequest.SingleData item : result) {
            BigDecimal grade = item.getGrade();
            if (grade.compareTo(BigDecimal.ZERO) < 0) {
                throw new ChemicalException(CS008);
            }
            if (grade.compareTo(BigDecimal.ONE) > 0) {
                throw new ChemicalException(CS008);
            }

        }


    }


}
