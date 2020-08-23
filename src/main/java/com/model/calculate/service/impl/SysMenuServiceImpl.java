package com.model.calculate.service.impl;

import com.model.calculate.domain.MenuVo;
import com.model.calculate.service.SysMenuService;
import com.model.calculate.util.TreeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Override
    public Map<String, Object> menu() {
        Map<String, Object> map = new HashMap<>(16);
        Map<String, Object> home = new HashMap<>(16);
        Map<String, Object> logo = new HashMap<>(16);
        List<MenuVo> menuInfo = new ArrayList<>();
        MenuVo menuVO = new MenuVo();
        menuVO.setId(2L);
        menuVO.setPid(1L);
        menuVO.setHref("http://www.baidu.com");
        menuVO.setTitle("模型计算");
        menuVO.setIcon("");
        menuVO.setTarget("");
        menuInfo.add(menuVO);
        map.put("menuInfo", TreeUtil.toTree(menuInfo, 0L));
        home.put("title", "首页");
        home.put("href", "/page/welcome-1");//控制器路由,自行定义
        logo.put("title", "后台管理系统");
        logo.put("image", "/static/images/back.jpg");//静态资源文件路径,可使用默认的logo.png
        map.put("homeInfo", "{title: '首页',href: '/ruge-web-admin/page/welcome.html'}}");
        map.put("logoInfo", "{title: 'RUGE ADMIN',image: 'images/logo.png'}");
        return map;
    }
}
