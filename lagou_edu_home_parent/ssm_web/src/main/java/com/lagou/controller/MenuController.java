package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
    * 查询所有菜单信息
    * */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true, 200, "查询所有菜单信息成功", allMenu);
    }

    /*
    * 回显菜单信息
    * */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id) {
        // 根据传过来的id值判断是更新还是添加操作，如果是新增菜单，则id值为-1
        if (id == -1) {
            // 新增菜单，不需要回显菜单信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            // 封装数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", subMenuListByPid);
            return new ResponseResult(true, 200, "添加回显成功", map);
        }
        else {
            // 修改操作 回显父级菜单列表&当前菜单详细信息
            Menu menu = menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            // 封装数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", subMenuListByPid);
            return new ResponseResult(true, 200, "修改回显成功", map);
        }
    }

}
