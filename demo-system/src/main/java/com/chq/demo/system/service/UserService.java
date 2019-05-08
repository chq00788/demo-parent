package com.chq.demo.system.service;


import com.chq.demo.common.model.system.PermissionModel;
import com.chq.demo.common.model.system.RoleModel;
import com.chq.demo.common.model.system.UserModel;
import com.chq.demo.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 描述：用户管理 服务实现层
 *
 * @author CHQ
 * @date 2019-01-19
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 查询数据
     *
     * @return
     */
    public List<UserModel> selectList(Map<String, Object> searchMap) {
        return userDao.selectList(searchMap);
    }


    /**
     * 新增数据
     *
     * @param model
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(UserModel model, Integer[] roleIds) {
        model.setIsDelete(0);
        model.setIsUsable("1");
        model.setUserType(2);
        model.setSalt("123456");
        model.setPassword(model.getPassword());
        userDao.insert(model);
        for (Integer roleId : roleIds) {
            userDao.saveUserRole(model.getId(), roleId);
        }
    }

    /**
     * 更新数据
     *
     * @param model
     */
    public void update(UserModel model) {
        userDao.update(model);
    }

    /**
     * 删除数据(逻辑删除)
     *
     * @param id
     */
    public void delete(Integer id) {
        UserModel user = getById(id);
        user.setIsDelete(1);
        userDao.update(user);
    }

    /**
     * 根据ID查询数据
     *
     * @param id
     */
    public UserModel getById(Integer id) {
        return userDao.getById(id);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    public UserModel getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    /**
     * 根据用户名查询用户菜单权限
     *
     * @param username
     * @return
     */
    public List<PermissionModel> getMenusByUsername(String username) {
        UserModel user = userDao.getMenusByUsername(username);
        List<PermissionModel> roots = new ArrayList<>();
        HashSet<PermissionModel> perms = new HashSet<>();
        if (null != user) {
            for (RoleModel role : user.getRoleList()) {
                perms.addAll(role.getPermissionList());
            }
            List<PermissionModel> menus = new ArrayList<>(perms);

            for (PermissionModel model : menus) {
                if (Integer.valueOf(0).equals(model.getPermPid())) {
                    model.setChildren(createMenus(menus, model.getId()));
                    roots.add(model);
                }
            }
        }
        return roots;
    }

    /**
     * 组织菜单信息
     *
     * @param menuList
     * @param pid
     * @return
     */
    private List<PermissionModel> createMenus(List<PermissionModel> menuList, Integer pid) {
        List<PermissionModel> menus = new ArrayList<>();
        for (PermissionModel model : menuList) {
            if (pid.equals(model.getPermPid())) {
                menus.add(model);
            }
        }
        return menus;
    }


    /**
     * 根据用户名查询用户权限信息
     *
     * @param username
     * @return
     */
    public List<PermissionModel> getPermissionsByUsername(String username) {
        //先从redis数据库查如果没有再从数据查
        List<PermissionModel> redisPermissions = (List<PermissionModel>) redisTemplate.opsForValue().get("permission-username-" + username);
        if (null != redisPermissions) {
            return redisPermissions;
        }
        UserModel user = userDao.getMenusByUsername(username);
        HashSet<PermissionModel> perms = new HashSet<>();
        if (null != user) {
            for (RoleModel role : user.getRoleList()) {
                perms.addAll(role.getPermissionList());
            }
        }
        List<PermissionModel> permissions = new ArrayList<>(perms);
        redisTemplate.opsForValue().set("permission-username-" + username, permissions);
        return permissions;
    }
}