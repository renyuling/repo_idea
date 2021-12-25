package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    /*
    * 用户分页&多条件组合查询
    * */
    public List<User> findAllUserByPage(UserVo userVo);

    /*
    * 修改用户状态
    * */
    public void updateUserStatus(Integer id, String status);

    /*
    * 用户登录（根据用户名查询具体用户信息）
    * */
    public User login(User user);

    /*
    * 根据用户ID查询关联的角色信息
    * */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
    * 根据用户ID清空该用户关联的角色中间表
    * */
    public void deleteUserContextRoleById(Integer id);

    /*
    * 给用户分配角色
    * */
    public void UserContextRole(User_Role_relation user_role_relation);

    /*
    * 根据角色Id查询父级菜单
    * */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
    * 根据pid查询子菜单信息
    * */
    public List<Menu> findSubMenuByPid(Integer pid);

    /*
    * 通过角色ID获取用户拥有的资源权限信息
    * */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

}
