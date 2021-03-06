/*
 * Copyright 2005-2016 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.entity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * Entity - 促销
 * 
 * @author SHOP++ Team
 * @version 5.0
 */
@Entity
@Table(name = "xx_promotion")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_promotion")
public class Promotion extends OrderEntity<Long> {

	private static final long serialVersionUID = 3536993535267962279L;

	/** 路径前缀 */
	private static final String PATH_PREFIX = "/promotion/content";

	/** 路径后缀 */
	private static final String PATH_SUFFIX = ".jhtml";

	/**
	 * 类型
	 */
	public enum Type {

		/** 折扣 */
		discount,

		/** 满减 */
		fullReduction

	}

	/** 名称 */
	private String name;

	/** 标题 */
	private String title;

	/** 类型 */
	private Promotion.Type type;

	/** 图片 */
	private String image;

	/** 起始日期 */
	private Date beginDate;

	/** 结束日期 */
	private Date endDate;

	/** 最小商品价格 */
	private BigDecimal minimumPrice;

	/** 最大商品价格 */
	private BigDecimal maximumPrice;

	/** 最小商品数量 */
	private Integer minimumQuantity;

	/** 最大商品数量 */
	private Integer maximumQuantity;

	/** 价格运算表达式 */
	private String priceExpression;

	/** 积分运算表达式 */
	private String pointExpression;

	/** 是否免运费 */
	private Boolean isFreeShipping;

	/** 是否允许使用优惠券 */
	private Boolean isCouponAllowed;

	/** 介绍 */
	private String introduction;

	/** 条件金额 */
	private BigDecimal conditionsAmount;

	/** 减免金额 */
	private BigDecimal creditAmount;

	/** 条件数量 */
	private Integer conditionsNumber;

	/** 减免数量 */
	private Integer creditNumber;

	/** 金额折扣 */
	private Double discount;

	/** 条件积分 */
	private Integer conditionsPoint;

	/** 调整积分 */
	private Integer adjustmentPoint;

	/** 是否启用 */
	private Boolean isEnabled;

	/** 店铺 */
	private Store store;

	/** 允许参加会员等级 */
	private Set<MemberRank> memberRanks = new HashSet<MemberRank>();

	/** 赠送优惠券 */
	private Set<Coupon> coupons = new HashSet<Coupon>();

	/** 赠品 */
	private Set<Product> gifts = new HashSet<Product>();

	/** 货品 */
	private Set<Goods> goods = new HashSet<Goods>();

	/** 商品分类 */
	private Set<ProductCategory> productCategories = new HashSet<ProductCategory>();

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	@Column(nullable = false)
	public Promotion.Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(Promotion.Type type) {
		this.type = type;
	}

	/**
	 * 获取图片
	 * 
	 * @return 图片
	 */
	@Length(max = 200)
	@Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
	public String getImage() {
		return image;
	}

	/**
	 * 设置图片
	 * 
	 * @param image
	 *            图片
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 获取起始日期
	 * 
	 * @return 起始日期
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置起始日期
	 * 
	 * @param beginDate
	 *            起始日期
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取结束日期
	 * 
	 * @return 结束日期
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置结束日期
	 * 
	 * @param endDate
	 *            结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取最小商品价格
	 * 
	 * @return 最小商品价格
	 */
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public BigDecimal getMinimumPrice() {
		return minimumPrice;
	}

	/**
	 * 设置最小商品价格
	 * 
	 * @param minimumPrice
	 *            最小商品价格
	 */
	public void setMinimumPrice(BigDecimal minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	/**
	 * 获取最大商品价格
	 * 
	 * @return 最大商品价格
	 */
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public BigDecimal getMaximumPrice() {
		return maximumPrice;
	}

	/**
	 * 设置最大商品价格
	 * 
	 * @param maximumPrice
	 *            最大商品价格
	 */
	public void setMaximumPrice(BigDecimal maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	/**
	 * 获取最小商品数量
	 * 
	 * @return 最小商品数量
	 */
	@Min(0)
	public Integer getMinimumQuantity() {
		return minimumQuantity;
	}

	/**
	 * 设置最小商品数量
	 * 
	 * @param minimumQuantity
	 *            最小商品数量
	 */
	public void setMinimumQuantity(Integer minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}

	/**
	 * 获取最大商品数量
	 * 
	 * @return 最大商品数量
	 */
	@Min(0)
	public Integer getMaximumQuantity() {
		return maximumQuantity;
	}

	/**
	 * 设置最大商品数量
	 * 
	 * @param maximumQuantity
	 *            最大商品数量
	 */
	public void setMaximumQuantity(Integer maximumQuantity) {
		this.maximumQuantity = maximumQuantity;
	}

	/**
	 * 获取价格运算表达式
	 * 
	 * @return 价格运算表达式
	 */
	public String getPriceExpression() {
		return priceExpression;
	}

	/**
	 * 设置价格运算表达式
	 * 
	 * @param priceExpression
	 *            价格运算表达式
	 */
	public void setPriceExpression(String priceExpression) {
		this.priceExpression = priceExpression;
	}

	/**
	 * 获取积分运算表达式
	 * 
	 * @return 积分运算表达式
	 */
	public String getPointExpression() {
		return pointExpression;
	}

	/**
	 * 设置积分运算表达式
	 * 
	 * @param pointExpression
	 *            积分运算表达式
	 */
	public void setPointExpression(String pointExpression) {
		this.pointExpression = pointExpression;
	}

	/**
	 * 获取是否免运费
	 * 
	 * @return 是否免运费
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIsFreeShipping() {
		return isFreeShipping;
	}

	/**
	 * 设置是否免运费
	 * 
	 * @param isFreeShipping
	 *            是否免运费
	 */
	public void setIsFreeShipping(Boolean isFreeShipping) {
		this.isFreeShipping = isFreeShipping;
	}

	/**
	 * 获取是否允许使用优惠券
	 * 
	 * @return 是否允许使用优惠券
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIsCouponAllowed() {
		return isCouponAllowed;
	}

	/**
	 * 设置是否允许使用优惠券
	 * 
	 * @param isCouponAllowed
	 *            是否允许使用优惠券
	 */
	public void setIsCouponAllowed(Boolean isCouponAllowed) {
		this.isCouponAllowed = isCouponAllowed;
	}

	/**
	 * 获取介绍
	 * 
	 * @return 介绍
	 */
	@Lob
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * 设置介绍
	 * 
	 * @param introduction
	 *            介绍
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * 获取条件金额
	 * 
	 * @return 条件金额
	 */
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public BigDecimal getConditionsAmount() {
		return conditionsAmount;
	}

	/**
	 * 设置条件金额
	 * 
	 * @param conditionsAmount
	 *            条件金额
	 */
	public void setConditionsAmount(BigDecimal conditionsAmount) {
		this.conditionsAmount = conditionsAmount;
	}

	/**
	 * 获取减免金额
	 * 
	 * @return 减免金额
	 */
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	/**
	 * 设置减免金额
	 * 
	 * @param creditAmount
	 *            减免金额
	 */
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * 获取条件数量
	 * 
	 * @return 条件数量
	 */
	@Min(0)
	public Integer getConditionsNumber() {
		return conditionsNumber;
	}

	/**
	 * 设置条件数量
	 * 
	 * @param conditionsNumber
	 *            条件数量
	 */
	public void setConditionsNumber(Integer conditionsNumber) {
		this.conditionsNumber = conditionsNumber;
	}

	/**
	 * 获取减免数量
	 * 
	 * @return 减免数量
	 */
	@Min(0)
	public Integer getCreditNumber() {
		return creditNumber;
	}

	/**
	 * 设置满减数量
	 * 
	 * @param creditNumber
	 *            减免数量
	 */
	public void setCreditNumber(Integer creditNumber) {
		this.creditNumber = creditNumber;
	}

	/**
	 * 获取折扣
	 * 
	 * @return 折扣
	 */
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public Double getDiscount() {
		return discount;
	}

	/**
	 * 设置折扣
	 * 
	 * @param discount
	 *            折扣
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * 获取条件积分
	 * 
	 * @return 条件积分
	 */
	@Min(0)
	public Integer getConditionsPoint() {
		return conditionsPoint;
	}

	/**
	 * 设置条件积分
	 * 
	 * @param conditionsPoint
	 *            条件积分
	 */
	public void setConditionsPoint(Integer conditionsPoint) {
		this.conditionsPoint = conditionsPoint;
	}

	/**
	 * 获取调整积分
	 * 
	 * @return 调整积分
	 */
	@Min(0)
	public Integer getAdjustmentPoint() {
		return adjustmentPoint;
	}

	/**
	 * 设置调整积分
	 * 
	 * @param adjustmentPoint
	 *            调整积分
	 */
	public void setAdjustmentPoint(Integer adjustmentPoint) {
		this.adjustmentPoint = adjustmentPoint;
	}

	/**
	 * 获取是否启用
	 * 
	 * @return 是否启用
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否启用
	 * 
	 * @param isEnabled
	 *            是否启用
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * 获取店铺
	 * 
	 * @return 店铺
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public Store getStore() {
		return store;
	}

	/**
	 * 设置店铺
	 * 
	 * @param store
	 *            店铺
	 */
	public void setStore(Store store) {
		this.store = store;
	}

	/**
	 * 获取允许参加会员等级
	 * 
	 * @return 允许参加会员等级
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "xx_promotion_member_rank")
	public Set<MemberRank> getMemberRanks() {
		return memberRanks;
	}

	/**
	 * 设置允许参加会员等级
	 * 
	 * @param memberRanks
	 *            允许参加会员等级
	 */
	public void setMemberRanks(Set<MemberRank> memberRanks) {
		this.memberRanks = memberRanks;
	}

	/**
	 * 获取赠送优惠券
	 * 
	 * @return 赠送优惠券
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "xx_promotion_coupon")
	public Set<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * 设置赠送优惠券
	 * 
	 * @param coupons
	 *            赠送优惠券
	 */
	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	/**
	 * 获取赠品
	 * 
	 * @return 赠品
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "xx_promotion_gift")
	public Set<Product> getGifts() {
		return gifts;
	}

	/**
	 * 设置赠品
	 * 
	 * @param gifts
	 *            赠品
	 */
	public void setGifts(Set<Product> gifts) {
		this.gifts = gifts;
	}

	/**
	 * 获取货品
	 * 
	 * @return 货品
	 */
	@ManyToMany(mappedBy = "promotions", fetch = FetchType.LAZY)
	public Set<Goods> getGoods() {
		return goods;
	}

	/**
	 * 设置货品
	 * 
	 * @param goods
	 *            货品
	 */
	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}

	/**
	 * 获取商品分类
	 * 
	 * @return 商品分类
	 */
	@ManyToMany(mappedBy = "promotions", fetch = FetchType.LAZY)
	@OrderBy("order asc")
	public Set<ProductCategory> getProductCategories() {
		return productCategories;
	}

	/**
	 * 设置商品分类
	 * 
	 * @param productCategories
	 *            商品分类
	 */
	public void setProductCategories(Set<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	/**
	 * 获取路径
	 * 
	 * @return 路径
	 */
	@Transient
	public String getPath() {
		return getId() != null ? PATH_PREFIX + "/" + getId() + PATH_SUFFIX : null;
	}

	/**
	 * 判断是否已开始
	 * 
	 * @return 是否已开始
	 */
	@Transient
	public boolean hasBegun() {
		return getBeginDate() == null || !getBeginDate().after(new Date());
	}

	/**
	 * 判断是否已结束
	 * 
	 * @return 是否已结束
	 */
	@Transient
	public boolean hasEnded() {
		return getEndDate() != null && !getEndDate().after(new Date());
	}

	/**
	 * 计算促销价格
	 * 
	 * @param price
	 *            商品价格
	 * @param quantity
	 *            商品数量
	 * @return 促销价格
	 */
	@Transient
	public BigDecimal calculatePrice(BigDecimal price, Integer quantity) {
		if (price == null || quantity == null || StringUtils.isEmpty(getPriceExpression())) {
			return price;
		}
		BigDecimal result = BigDecimal.ZERO;
		try {
			Binding binding = new Binding();
			binding.setVariable("quantity", quantity);
			binding.setVariable("price", price);
			GroovyShell groovyShell = new GroovyShell(binding);
			result = new BigDecimal(groovyShell.evaluate(getPriceExpression()).toString(), MathContext.DECIMAL32);
		} catch (Exception e) {
			return price;
		}
		if (result.compareTo(price) > 0) {
			return price;
		}
		return result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;
	}

	/**
	 * 计算促销赠送积分
	 * 
	 * @param point
	 *            赠送积分
	 * @param quantity
	 *            商品数量
	 * @return 促销赠送积分
	 */
	@Transient
	public Long calculatePoint(Long point, Integer quantity) {

		if (point == null || quantity == null || StringUtils.isEmpty(getPointExpression())) {
			return point;
		}
		Long result = 0L;
		try {
			Binding binding = new Binding();
			binding.setVariable("quantity", quantity);
			binding.setVariable("point", point);
			GroovyShell groovyShell = new GroovyShell(binding);
			result = Long.valueOf(groovyShell.evaluate(getPointExpression()).toString());
		} catch (Exception e) {
			return point;
		}
		if (result < point) {
			return point;
		}
		return result > 0L ? result : 0L;
	}

	/**
	 * 判断是否已过期
	 * 
	 * @return 是否已过期
	 */
	@Transient
	public boolean hasExpired() {
		return false;
	}

	/**
	 * 删除前处理
	 */
	@PreRemove
	public void preRemove() {
		Set<Goods> goodsList = getGoods();
		if (goodsList != null) {
			for (Goods goods : goodsList) {
				goods.getPromotions().remove(this);
			}
		}
		Set<ProductCategory> productCategories = getProductCategories();
		if (productCategories != null) {
			for (ProductCategory productCategory : productCategories) {
				productCategory.getPromotions().remove(this);
			}
		}
	}

}