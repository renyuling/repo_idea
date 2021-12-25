package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /*
    * （条件）查询所有角色信息
    * */
    public List<Role> findAllRole(Role role);

    /*
    * 根据角色id查询出其关联的菜单
    * */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /*
    * 根据roleid清空中间表关联的关系
    * */
    public void deleteRoleContextMenu(Integer rid);

    /*
    * 为角色分配菜单信息
    * */
    public void roleContextMenu(Role_menu_relation role_menu_relation);


    /*
    * 删除角色
    * */
    public void deleteRole(Integer roleid);
}
