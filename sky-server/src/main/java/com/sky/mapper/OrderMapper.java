package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    //超时(15分钟): select * from orders where status = 待付款 and order_time < 当前时间-15分钟
    //根据订单状态和下单时间查询订单
    @Select("select * from orders where status = #{status} and order_time < {orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

    @Update("update orders set status = #{status}, cancel_reason = #{CancelReason}, cancel_time = #{CancelTime}")
    void update(Orders orders);
}
