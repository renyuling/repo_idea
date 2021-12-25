package com.lagou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);
        PageInfo<User> userPageInfo = new PageInfo<>(allUserByPage);

        return userPageInfo;
    }

    @Override
    public void updateUserStatus(Integer id, String status) {
        userMapper.updateUserStatus(id, status);
    }


    /*
    * 用户登录
    * */
    @Override
    public User login(User user) throws Exception{
        // 1.调用mapper方法
        User user2 = userMapper.login(user);
        if (user2 != null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())) {
            return user2;
        }
        else {
            return null;
        }
    }

    /*
    * 分配角色（回显）
    * */
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void UserContextRole(UserVo userVo) {
        // 1.根据用户ID清空对应角色表
        userMapper.deleteUserContextRoleById(userVo.getUserId());
        
        // 2.给用户分配角色
        List<Integer> roleIdList = userVo.getRoleIdList();
        for (Integer roleId : roleIdList) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.UserContextRole(user_role_relation);
        }
    }

    /*
     * 获取用户权限，进行菜单动态展示
     * */
    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        // 1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);
        // 2.获取角色ID，保存到list集合中
        List<Integer> roleIds = roleList.stream()
                .map(Role::getId)
                .collect(Collectors.toList());
        // 3.根据角色Id查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        // 4.通过父菜单查询其关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        // 5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenu);
        map.put("resourceList", resourceList);
        return new ResponseResult(true, 200, "获取用户权限信息成功", map);
    }
}
