package com.chq.demo.shiro.jwt.client;

import com.chq.demo.common.entity.Response;
import com.chq.demo.common.model.system.PermissionModel;
import com.chq.demo.common.model.system.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author CHQ
 */
@FeignClient("demo-system")
public interface SystemService {

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping("/system/user/getList")
    String getUser();

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @RequestMapping("/system/user/getByUsername")
    Response<UserModel> getByUsername(@RequestParam(name = "username") String username);

    /**
     * 根据用户名查询权限信息
     *
     * @param username
     * @return
     */
    @RequestMapping("/system/user/getPermissionsByUsername")
    Response<List<PermissionModel>> getPermissionsByUsername(@RequestParam(name = "username") String username);

}
