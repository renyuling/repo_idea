package com.lagou.service;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    /*
    * 查询所有父子级菜单
    * */
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);
        return subMenuListByPid;
    }

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> menuList = menuMapper.findAllMenu();
        return menuList;
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }
}
