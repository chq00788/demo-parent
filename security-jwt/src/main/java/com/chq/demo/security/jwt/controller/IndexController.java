package com.chq.demo.security.jwt.controller;

import com.chq.demo.security.jwt.common.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */
@RestController
public class IndexController {

    @RequestMapping("index")
    public ResultUtil index() {
        return ResultUtil.success();
    }
}
