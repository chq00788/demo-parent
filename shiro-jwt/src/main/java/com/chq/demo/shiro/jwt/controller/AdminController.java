package com.chq.demo.shiro.jwt.controller;

import com.chq.demo.shiro.jwt.common.entity.ResultMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHQ
 * @Description
 * @date 2019/4/2
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        ResultMap resultMap = new ResultMap();
        return resultMap.success().message("您拥有管理员权限，可以获得该接口的信息！");
    }
}
