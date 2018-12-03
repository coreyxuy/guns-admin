package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.House;
import cn.stylefeng.guns.modular.system.service.IHouseService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-12-03 14:06:45
 */
@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {

    private String PREFIX = "/system/house/";

    @Autowired
    private IHouseService houseService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "house.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/house_add")
    public String houseAdd() {
        return PREFIX + "house_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/house_update/{houseId}")
    public String houseUpdate(@PathVariable Integer houseId, Model model) {
        House house = houseService.selectById(houseId);
        model.addAttribute("item",house);
        LogObjectHolder.me().set(house);
        return PREFIX + "house_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //判断是否为空 查询全部
        if (ToolUtil.isEmpty(condition)){
            return houseService.selectList(null);
        }else {
            //模糊查询
            EntityWrapper<House> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("house_user",condition);
            return houseService.selectList(entityWrapper);
        }

    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(House house) {
        houseService.insert(house);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer houseId) {
        houseService.deleteById(houseId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(House house) {
        houseService.updateById(house);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{houseId}")
    @ResponseBody
    public Object detail(@PathVariable("houseId") Integer houseId) {
        return houseService.selectById(houseId);
    }
}
