package com.shengrong.chemicalsystem.controller;

import com.shengrong.chemicalsystem.controller.request.CreateVersionRequest;
import com.shengrong.chemicalsystem.controller.request.OrderRequest;
import com.shengrong.chemicalsystem.controller.request.RelatePersonRequest;
import com.shengrong.chemicalsystem.controller.response.VersionResponse;
import com.shengrong.chemicalsystem.controller.response.common.PageResultResponse;
import com.shengrong.chemicalsystem.dao.PersonDao;
import com.shengrong.chemicalsystem.dao.VersionDao;
import com.shengrong.chemicalsystem.ecxeption.ChemicalException;
import com.shengrong.chemicalsystem.model.entity.OrderEntity;
import com.shengrong.chemicalsystem.model.entity.PersonEntity;
import com.shengrong.chemicalsystem.model.entity.VersionEntity;
import com.shengrong.chemicalsystem.model.entity.commom.PageEntity;
import com.shengrong.chemicalsystem.service.OrderService;
import com.shengrong.chemicalsystem.service.PersonService;
import com.shengrong.chemicalsystem.service.VersionService;
import com.shengrong.chemicalsystem.utils.DateUtils;
import com.shengrong.chemicalsystem.utils.IdUtils;
import com.shengrong.chemicalsystem.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.shengrong.chemicalsystem.ecxeption.ExceptionCodeEnum.CS001;

/**
 * @author xueke274
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class VersionController {

    @Resource
    private VersionDao versionDao;

    @Resource
    private PersonDao personDao;

    @Resource
    private PersonService personService;

    @Resource
    private VersionService versionService;

    @RequestMapping(method = RequestMethod.GET, value = "/version")
    public Object getVersion(){
        VersionResponse response = new VersionResponse();



        VersionEntity query = new VersionEntity();
        List<VersionEntity> data = versionService.queryPage(query, new PageEntity()).getData();
        if (CollectionUtils.isEmpty(data)) {
            response.setVersionDetails(new ArrayList<>());
            return ResponseUtils.getDataResponse(response);
        }

        List<VersionResponse.VersionDetail> result = new ArrayList<>();
        for (VersionEntity single : data) {
            VersionResponse.VersionDetail item = new VersionResponse.VersionDetail();
            String versionName = single.getName();
            String versionId = single.getId();

            item.setId(versionId);
            item.setVersion(versionName);


            PersonEntity q = new PersonEntity();
            q.setVersionId(versionId);
            List<PersonEntity> persons = personService.queryPage(q, new PageEntity()).getData();
            if (CollectionUtils.isEmpty(persons)){
                item.setNames(new ArrayList<>());
            }else {
                item.setNames(persons.stream().map(PersonEntity::getName).collect(Collectors.toList()));
            }

            result.add(item);
        }

        response.setVersionDetails(result);
        return ResponseUtils.getDataResponse(response);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/version")
    public Object createVersion(@RequestBody CreateVersionRequest request){
        if (StringUtils.isEmpty(request.getName())){
            throw new ChemicalException(CS001);
        }
        VersionEntity entity = new VersionEntity();
        entity.setId(IdUtils.getUUID());
        entity.setName(request.getName());
        entity.setCreateTime(DateUtils.getCurrentTime());
        entity.setModifyTime(DateUtils.getCurrentTime());
        versionDao.insert(entity);
        return ResponseUtils.getDefResponse();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/version/relate")
    public Object relatePerson(@RequestBody RelatePersonRequest request){
        String versionId = request.getVersionId();
        List<String> names = request.getNames();
        if (StringUtils.isEmpty(versionId)) {
            throw new ChemicalException(CS001);
        }
        if (CollectionUtils.isEmpty(names)) {
            throw new ChemicalException(CS001);
        }

        PersonEntity entity = new PersonEntity();


        entity.setVersionId(versionId);
        for (String name : names) {
            entity.setId(IdUtils.getUUID());
            entity.setName(name.trim());
            entity.setCreateTime(DateUtils.getCurrentTime());
            entity.setModifyTime(DateUtils.getCurrentTime());
            personDao.insert(entity);
        }
        return ResponseUtils.getDefResponse();
    }


    private VersionResponse.VersionDetail def120() {
        VersionResponse.VersionDetail detail = new VersionResponse.VersionDetail();
        detail.setVersion("V1.2.0");
        String[] names =  {"胡世添", "薛科", "马玉洲", "陈天雄", "杨松涛", "李思岑", "涂昆", "张富贤", "涂松", "马晓川", "李奇", "张亚飞", "黄鑫", "徐房和", "杜彬菀"};
        detail.setNames(Arrays.asList(names));
        return detail;
    }

    private VersionResponse.VersionDetail def121() {
        VersionResponse.VersionDetail detail = new VersionResponse.VersionDetail();
        detail.setVersion("V1.2.1");
        String[] names  = {"胡世添", "薛科", "马玉洲", "陈天雄", "杨松涛", "李思岑", "涂昆", "张富贤", "涂松", "马晓川", "李奇", "胡春婉", "黄鑫"};
        detail.setNames(Arrays.asList(names));
        return detail;
    }

}
