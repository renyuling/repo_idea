package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;

import java.util.List;

public interface UserService {
    /*
    * 用户分页&多条件查询
    * */
    public PageInfo findAllUserByPage(UserVo userVo);

    /*
    * 修改用户状态
    * */
    public void updateUserStatus(Integer id, String status);

    /*
    * 用户登录
    * */
    public User login(User user) throws Exception;

    /*
    * 分配角色（回显）
    * */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
     * 给用户分配角色
     * */
    public void UserContextRole(UserVo userVo);

    /*
    * 获取用户权限，进行菜单动态展示
    * */
    public ResponseResult getUserPermissions(Integer userId);
}
