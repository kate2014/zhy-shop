/*
 * Copyright 2005-2016 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import net.shopxx.Order;
import net.shopxx.dao.SvcDao;
import net.shopxx.entity.Store;
import net.shopxx.entity.StoreRank;
import net.shopxx.entity.Svc;

/**
 * Dao - 服务
 * 
 * @author SHOP++ Team
 * @version 5.0
 */
@Repository("svcDaoImpl")
public class SvcDaoImpl extends BaseDaoImpl<Svc, Long> implements SvcDao {

	public Svc findBySn(String sn) {
		if (StringUtils.isEmpty(sn)) {
			return null;
		}
		String jpql = "select svc from Svc svc where lower(svc.sn) = lower(:sn)";
		try {
			return entityManager.createQuery(jpql, Svc.class).setParameter("sn", sn).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Svc> find(Store store, String promotionPluginId, StoreRank storeRank, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Svc> criteriaQuery = criteriaBuilder.createQuery(Svc.class);
		Root<Svc> root = criteriaQuery.from(Svc.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (store != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("store"), store));
		}
		if (promotionPluginId != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("promotionPluginId"), promotionPluginId));
		}
		if (storeRank != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("storeRank"), storeRank));
		}
		criteriaQuery.where(restrictions);
		if (orders == null || orders.isEmpty()) {
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdDate")));
		}
		return super.findList(criteriaQuery, null, null, null, orders);
	}

}