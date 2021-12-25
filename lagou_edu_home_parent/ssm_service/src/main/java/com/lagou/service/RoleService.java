package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    /*
    * 查询所有的角色（条件）
    * */
    public List<Role> findAllRole(Role role);

    /*
     * 根据角色id查询出其关联的菜单
     * */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /*
    * 为角色分配菜单
    * */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /*
    * 删除角色
    * */
    public void deleteRole(Integer roleid);
}
