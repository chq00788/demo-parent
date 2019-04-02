package com.chq.demo.shiro.jwt.controller;

import com.chq.demo.shiro.jwt.common.entity.ResultMap;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    @RequiresRoles({"user"})
    public ResultMap getMessage() {
        ResultMap resultMap = new ResultMap();
        return resultMap.success().message("您拥有用户权限，可以获得该接口的信息！");
    }
}
