/*
 * Copyright 2005-2016 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao;

import net.shopxx.entity.OrderPayment;

/**
 * Dao - 订单支付
 * 
 * @author SHOP++ Team
 * @version 5.0
 */
public interface OrderPaymentDao extends BaseDao<OrderPayment, Long> {

	/**
	 * 根据编号查找订单支付
	 * 
	 * @param sn
	 *            编号(忽略大小写)
	 * @return 订单支付，若不存在则返回null
	 */
	OrderPayment findBySn(String sn);

}