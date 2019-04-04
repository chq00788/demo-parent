package com.chq.demo.shiro.jwt.common.config;

import com.chq.demo.shiro.jwt.common.entity.JwtToken;
import com.chq.demo.shiro.jwt.common.utils.JwtUtil;
import com.chq.demo.shiro.jwt.system.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author CHQ
 * @Description 自定义 Realm
 * @date 2019/4/2
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token认证失败！");
        }
        String password = userService.getPassword(username);
        if (password == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        if (!JwtUtil.verify(token, username, password)) {
            throw new AuthenticationException("用户名或密码错误");
        }
        int ban = userService.checkUserBanStatus(username);
        if (ban == 1) {
            throw new AuthenticationException("该账号已被禁用!");
        }
        return new SimpleAuthenticationInfo(token, token, "MyRealm");
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        //获取用户名
        String username = JwtUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取该用户所有权限
        Set<String> permissionSet = userService.getPermission(username);
        //需要将 permission 封装到 Set 作为info.setStringPermissions() 的参数
        //设置该用户拥有和权限
        info.setStringPermissions(permissionSet);
        return info;
    }

}
