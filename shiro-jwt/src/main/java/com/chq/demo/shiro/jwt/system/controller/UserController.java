package com.chq.demo.shiro.jwt.system.controller;

import com.chq.demo.shiro.jwt.client.SystemService;
import com.chq.demo.shiro.jwt.common.entity.ResultMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHQ
 * @Description
 * @date 2019/4/2
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    @RequiresPermissions({"system:user:getMessage"})
    public ResultMap getMessage() {
        ResultMap resultMap = new ResultMap();
        System.out.println(systemService.getUser());
        return resultMap.success().message("您拥有用户权限，可以获得该接口的信息！");
    }

    @RequestMapping(value = "/getMessage2", method = RequestMethod.GET)
    @RequiresPermissions({"user:getMessage"})
    public ResultMap getMessage2() {
        ResultMap resultMap = new ResultMap();
        System.out.println(systemService.getUser());
        return resultMap.success().message("您拥有用户权限，可以获得该接口的信息！");
    }
}
