package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
//定时任务类, 定时处理订单状态
public class OrderTask {

    @Resource
    private OrderMapper orderMapper;
    //指定这个方法每隔10秒运行一次, 可以直接操作Mapper等
    //秒 分钟 小时 日 月 周 年

    //处理超时订单
    @Scheduled(cron = "0 * * * * ?")
    public void processTimeOutOrder(){
        log.info("每分钟处理超时订单:{}", LocalDateTime.now());

        //超时(15分钟): select * from orders where status = 待付款 and order_time < 当前时间-15分钟

        //当前时间-15分钟:
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, time);

        if (ordersList!=null && ordersList.size()>0){
            for (Orders orders:ordersList
                 ) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时, 自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }


    }


    //每天三点处理派送中订单
    @Scheduled(cron = "0 0 3 * * ?")
    public void processDeliveryOrder(){
        log.info("每天三点处理派送中订单");

        //select * from orders where status = 派送中 and order_time < 当前时间-3小时

        LocalDateTime time = LocalDateTime.now().plusHours(-3);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, time);

        if (ordersList!=null && ordersList.size()>0){
            for (Orders orders:ordersList
            ) {
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
            }
        }



    }






}
