package com.shengrong.chemicalsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shengrong.chemicalsystem.controller.request.TotalRequest;
import com.shengrong.chemicalsystem.controller.response.TotalResponse;
import com.shengrong.chemicalsystem.controller.response.VersionResponse;
import com.shengrong.chemicalsystem.controller.response.common.PageResultResponse;
import com.shengrong.chemicalsystem.dao.TotalDao;
import com.shengrong.chemicalsystem.ecxeption.ChemicalException;
import com.shengrong.chemicalsystem.model.entity.TotalEntity;
import com.shengrong.chemicalsystem.model.entity.commom.PageEntity;
import com.shengrong.chemicalsystem.service.TotalService;
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
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author xueke274
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TotalController {


    @Resource
    private TotalDao dao;

    @Resource
    private TotalService totalService;


    @RequestMapping(method = RequestMethod.POST, value = "/total")
    public Object add(@RequestBody TotalRequest request){

        this.checkGrade(request.getResult());





        TotalEntity entity = new TotalEntity();

        entity.setMvp(request.getMvp());
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
        if (StringUtils.isEmpty(entity.getMvp())) {
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


    @RequestMapping(method = RequestMethod.GET, value = "/total")
    public Object total(String version){
        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageNumber(0);
        pageEntity.setPageSize(100);
        TotalEntity query = new TotalEntity();
        query.setVersion(version);
        PageResultResponse<TotalEntity> page = totalService.queryPage(query, pageEntity);
        if (CollectionUtils.isEmpty(page.getData())) {
            return ResponseUtils.getDataResponse(new TotalResponse());
        }
        List<TotalEntity> data = page.getData();
        Map<String, Integer> mvpMap = new HashMap<>();
        List<TotalRequest.SingleData> allData = new ArrayList<>();
        for (TotalEntity totalEntity : data) {
            List<TotalRequest.SingleData> singleData = JSON.parseObject(totalEntity.getResult(), new TypeReference<List<TotalRequest.SingleData>>() {
            });
            allData.addAll(singleData);

            String mvp = totalEntity.getMvp();
            mvpMap.merge(mvp, 1, Integer::sum);

        }

        Map<String, TotalResponse.TotalDetail> map = new HashMap<>();
        for (TotalRequest.SingleData singleData : allData) {
            String name = singleData.getName();
            BigDecimal grade = singleData.getGrade();
            if (grade.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            if (map.get(name) == null) {
                TotalResponse.TotalDetail detail = new TotalResponse.TotalDetail();
                detail.setName(name);
                detail.setNum(1);
                detail.setResult(grade);

                map.put(name, detail);
            }else {
                TotalResponse.TotalDetail detail = map.get(name);
                int newNumber = detail.getNum() + 1;
                BigDecimal newDel = detail.getResult().add(grade);

                detail.setNum(newNumber);
                detail.setResult(newDel);

                map.put(name, detail);
            }
        }

        TotalResponse response = new TotalResponse();

        for (TotalResponse.TotalDetail value : map.values()) {
            BigDecimal result = value.getResult();
            BigDecimal decimal = new BigDecimal(value.getNum());
            value.setResult(result.divide(decimal, 2, RoundingMode.HALF_UP));
        }


        response.setTotals(new ArrayList<>(map.values()));
        response.setMvpArr(this.newMvpArr(mvpMap));
        return ResponseUtils.getDataResponse(response);
    }

    private List<String> newMvpArr(Map<String, Integer> mvpMap) {
        List<String> result = new ArrayList<>();
        Integer max = Collections.max(mvpMap.values());
        for (Map.Entry<String, Integer> entry : mvpMap.entrySet()) {
            if (max.equals(entry.getValue())){
                result.add(entry.getKey());
            }
        }
        return result;
    }

}
