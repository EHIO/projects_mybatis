package org.wg.mybatis.mapper;

import org.wg.mybatis.po.Orders;
import org.wg.mybatis.po.OrdersCustom;
import org.wg.mybatis.po.User;

import java.util.List;

/**
 * @author wg
 * @version 1.0
 * @date 2015-4-23 10:28:43
 */
public interface OrdersMapperCustom {

    /**
     * 查询订单关联查询用户信息
     */
    List<OrdersCustom> findOrdersUser() throws Exception;

    /**
     * 查询订单关联查询用户使用resultMap
     */
    List<Orders> findOrdersUserResultMap() throws Exception;

    /**
     * 查询订单(关联用户)及订单明细
     */
    List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;

    /**
     * 查询用户购买商品信息
     */
    List<User> findUserAndItemsResultMap() throws Exception;

    /**
     * 查询订单关联查询用户，用户信息是延迟加载
     */
    List<Orders> findOrdersUserLazyLoading() throws Exception;

}
