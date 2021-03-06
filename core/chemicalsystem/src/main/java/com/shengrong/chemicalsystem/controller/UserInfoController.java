package com.shengrong.chemicalsystem.controller;

import com.shengrong.chemicalsystem.controller.request.UserInfoRequest;
import com.shengrong.chemicalsystem.controller.response.common.PageResultResponse;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.model.entity.commom.PageEntity;
import com.shengrong.chemicalsystem.service.UserInfoService;
import com.shengrong.chemicalsystem.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public Object getPage(UserInfoRequest request){

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUsername(request.getUsername());

        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageNumber(request.getPageNumber());
        pageEntity.setPageSize(request.getPageSize());

        PageResultResponse<UserInfoEntity> response = userInfoService.queryPage(userInfoEntity, pageEntity);
        return ResponseUtils.getDataResponse(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public Object queryById(@PathVariable("id") String id){
        UserInfoEntity entity = userInfoService.queryById(id);
        return ResponseUtils.getDataResponse(entity);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public Object add(@RequestBody UserInfoEntity entity){
        userInfoService.insert(entity);
        return ResponseUtils.getDefResponse();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
    public Object modifyById(@RequestBody UserInfoEntity entity, @PathVariable("id") String id){
        entity.setId(id);
        userInfoService.updateById(entity);
        return ResponseUtils.getDefResponse();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public Object deleteById(@PathVariable("id") String id){
        userInfoService.deleteById(id);
        return ResponseUtils.getDefResponse();
    }
}
