package com.hmdp.controller;


import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.service.IShopTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 虎哥
 */
@RestController
@RequestMapping("/shop-type")
public class ShopTypeController {
    @Resource
    private IShopTypeService shopTypeService;

    /**
     * 缓存店铺类型信息
     */
    @GetMapping("list")
    public Result queryShopTypes() {
        List<ShopType> shopTypes = shopTypeService.queryShopTypes();
        if(shopTypes == null){
            return Result.fail("店铺类型不存在");
        }
        return Result.ok(shopTypes);
    }
}
