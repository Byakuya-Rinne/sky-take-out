package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishMapper;
import com.sky.mapper.DishFlavorMapper;
import com.sky.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;


    //新增菜品和口味
    @Override
    //多表操作, 用事务保证一致性
//    @EnableTransactionManagement 主程序 开启注解方式的事务管理
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        //向菜品表插入一条数据, 向口味插入多条
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dishMapper.insert(dish);

//        Dish dish的id来自DishMapper.xml的<insert id="insert" useGeneratedKeys="true" keyProperty="id">返回结果
        Long id = dish.getId();


        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null || flavors.size() > 0) {
            //dishFlavor就是flavors集合里每一个对象的别名, 无所谓
            flavors.forEach(dishFlavor -> {dishFlavor.setDishId(id);});
            dishFlavorMapper.insertBatch(flavors);
        }


        //D:\Code\java\Sky\sky-take-out\project-sky-admin-vue-ts\src\views\dish
        //恢复副本, 以取消对前端的更改( rules里删除上传图片的必要 )

    }
}
