package com.chq.demo.shiro.jwt.system.controller;

import com.chq.demo.shiro.jwt.common.entity.ResultMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHQ
 * @Description
 * @date 2019/4/2
 */
@RestController
@RequestMapping("/guest")
public class GuestController {

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    @RequiresPermissions({"system:guest:submitLogin"})
    public ResultMap submitLogin() {
        ResultMap resultMap = new ResultMap();
        return resultMap.success().message("您拥有获得该接口的信息的权限！");
    }
}
