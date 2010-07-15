/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.shopping.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.shopping.NoSuchOrderItemException;
import com.liferay.portlet.shopping.model.ShoppingOrderItem;
import com.liferay.portlet.shopping.model.impl.ShoppingOrderItemImpl;
import com.liferay.portlet.shopping.model.impl.ShoppingOrderItemModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       ShoppingOrderItemPersistence
 * @see       ShoppingOrderItemUtil
 * @generated
 */
public class ShoppingOrderItemPersistenceImpl extends BasePersistenceImpl<ShoppingOrderItem>
	implements ShoppingOrderItemPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = ShoppingOrderItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_ORDERID = new FinderPath(ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByOrderId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ORDERID = new FinderPath(ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByOrderId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(ShoppingOrderItem shoppingOrderItem) {
		EntityCacheUtil.putResult(ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemImpl.class, shoppingOrderItem.getPrimaryKey(),
			shoppingOrderItem);
	}

	public void cacheResult(List<ShoppingOrderItem> shoppingOrderItems) {
		for (ShoppingOrderItem shoppingOrderItem : shoppingOrderItems) {
			if (EntityCacheUtil.getResult(
						ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
						ShoppingOrderItemImpl.class,
						shoppingOrderItem.getPrimaryKey(), this) == null) {
				cacheResult(shoppingOrderItem);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(ShoppingOrderItemImpl.class.getName());
		EntityCacheUtil.clearCache(ShoppingOrderItemImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(ShoppingOrderItem shoppingOrderItem) {
		EntityCacheUtil.removeResult(ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemImpl.class, shoppingOrderItem.getPrimaryKey());
	}

	public ShoppingOrderItem create(long orderItemId) {
		ShoppingOrderItem shoppingOrderItem = new ShoppingOrderItemImpl();

		shoppingOrderItem.setNew(true);
		shoppingOrderItem.setPrimaryKey(orderItemId);

		return shoppingOrderItem;
	}

	public ShoppingOrderItem remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public ShoppingOrderItem remove(long orderItemId)
		throws NoSuchOrderItemException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ShoppingOrderItem shoppingOrderItem = (ShoppingOrderItem)session.get(ShoppingOrderItemImpl.class,
					new Long(orderItemId));

			if (shoppingOrderItem == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + orderItemId);
				}

				throw new NoSuchOrderItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					orderItemId);
			}

			return remove(shoppingOrderItem);
		}
		catch (NoSuchOrderItemException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ShoppingOrderItem removeImpl(ShoppingOrderItem shoppingOrderItem)
		throws SystemException {
		shoppingOrderItem = toUnwrappedModel(shoppingOrderItem);

		Session session = null;

		try {
			session = openSession();

			if (shoppingOrderItem.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(ShoppingOrderItemImpl.class,
						shoppingOrderItem.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(shoppingOrderItem);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemImpl.class, shoppingOrderItem.getPrimaryKey());

		return shoppingOrderItem;
	}

	public ShoppingOrderItem updateImpl(
		com.liferay.portlet.shopping.model.ShoppingOrderItem shoppingOrderItem,
		boolean merge) throws SystemException {
		shoppingOrderItem = toUnwrappedModel(shoppingOrderItem);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, shoppingOrderItem, merge);

			shoppingOrderItem.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemImpl.class, shoppingOrderItem.getPrimaryKey(),
			shoppingOrderItem);

		return shoppingOrderItem;
	}

	protected ShoppingOrderItem toUnwrappedModel(
		ShoppingOrderItem shoppingOrderItem) {
		if (shoppingOrderItem instanceof ShoppingOrderItemImpl) {
			return shoppingOrderItem;
		}

		ShoppingOrderItemImpl shoppingOrderItemImpl = new ShoppingOrderItemImpl();

		shoppingOrderItemImpl.setNew(shoppingOrderItem.isNew());
		shoppingOrderItemImpl.setPrimaryKey(shoppingOrderItem.getPrimaryKey());

		shoppingOrderItemImpl.setOrderItemId(shoppingOrderItem.getOrderItemId());
		shoppingOrderItemImpl.setOrderId(shoppingOrderItem.getOrderId());
		shoppingOrderItemImpl.setItemId(shoppingOrderItem.getItemId());
		shoppingOrderItemImpl.setSku(shoppingOrderItem.getSku());
		shoppingOrderItemImpl.setName(shoppingOrderItem.getName());
		shoppingOrderItemImpl.setDescription(shoppingOrderItem.getDescription());
		shoppingOrderItemImpl.setProperties(shoppingOrderItem.getProperties());
		shoppingOrderItemImpl.setPrice(shoppingOrderItem.getPrice());
		shoppingOrderItemImpl.setQuantity(shoppingOrderItem.getQuantity());
		shoppingOrderItemImpl.setShippedDate(shoppingOrderItem.getShippedDate());

		return shoppingOrderItemImpl;
	}

	public ShoppingOrderItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public ShoppingOrderItem findByPrimaryKey(long orderItemId)
		throws NoSuchOrderItemException, SystemException {
		ShoppingOrderItem shoppingOrderItem = fetchByPrimaryKey(orderItemId);

		if (shoppingOrderItem == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + orderItemId);
			}

			throw new NoSuchOrderItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				orderItemId);
		}

		return shoppingOrderItem;
	}

	public ShoppingOrderItem fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public ShoppingOrderItem fetchByPrimaryKey(long orderItemId)
		throws SystemException {
		ShoppingOrderItem shoppingOrderItem = (ShoppingOrderItem)EntityCacheUtil.getResult(ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
				ShoppingOrderItemImpl.class, orderItemId, this);

		if (shoppingOrderItem == null) {
			Session session = null;

			try {
				session = openSession();

				shoppingOrderItem = (ShoppingOrderItem)session.get(ShoppingOrderItemImpl.class,
						new Long(orderItemId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (shoppingOrderItem != null) {
					cacheResult(shoppingOrderItem);
				}

				closeSession(session);
			}
		}

		return shoppingOrderItem;
	}

	public List<ShoppingOrderItem> findByOrderId(long orderId)
		throws SystemException {
		return findByOrderId(orderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<ShoppingOrderItem> findByOrderId(long orderId, int start,
		int end) throws SystemException {
		return findByOrderId(orderId, start, end, null);
	}

	public List<ShoppingOrderItem> findByOrderId(long orderId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				orderId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<ShoppingOrderItem> list = (List<ShoppingOrderItem>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ORDERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_SHOPPINGORDERITEM_WHERE);

				query.append(_FINDER_COLUMN_ORDERID_ORDERID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(ShoppingOrderItemModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderId);

				list = (List<ShoppingOrderItem>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ShoppingOrderItem>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ORDERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ShoppingOrderItem findByOrderId_First(long orderId,
		OrderByComparator orderByComparator)
		throws NoSuchOrderItemException, SystemException {
		List<ShoppingOrderItem> list = findByOrderId(orderId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("orderId=");
			msg.append(orderId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchOrderItemException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ShoppingOrderItem findByOrderId_Last(long orderId,
		OrderByComparator orderByComparator)
		throws NoSuchOrderItemException, SystemException {
		int count = countByOrderId(orderId);

		List<ShoppingOrderItem> list = findByOrderId(orderId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("orderId=");
			msg.append(orderId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchOrderItemException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ShoppingOrderItem[] findByOrderId_PrevAndNext(long orderItemId,
		long orderId, OrderByComparator orderByComparator)
		throws NoSuchOrderItemException, SystemException {
		ShoppingOrderItem shoppingOrderItem = findByPrimaryKey(orderItemId);

		Session session = null;

		try {
			session = openSession();

			ShoppingOrderItem[] array = new ShoppingOrderItemImpl[3];

			array[0] = getByOrderId_PrevAndNext(session, shoppingOrderItem,
					orderId, orderByComparator, true);

			array[1] = shoppingOrderItem;

			array[2] = getByOrderId_PrevAndNext(session, shoppingOrderItem,
					orderId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ShoppingOrderItem getByOrderId_PrevAndNext(Session session,
		ShoppingOrderItem shoppingOrderItem, long orderId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHOPPINGORDERITEM_WHERE);

		query.append(_FINDER_COLUMN_ORDERID_ORDERID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(ShoppingOrderItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(orderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(shoppingOrderItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ShoppingOrderItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<ShoppingOrderItem> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<ShoppingOrderItem> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<ShoppingOrderItem> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<ShoppingOrderItem> list = (List<ShoppingOrderItem>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (orderByComparator != null) {
					query = new StringBundler(2 +
							(orderByComparator.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_SHOPPINGORDERITEM);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_SHOPPINGORDERITEM.concat(ShoppingOrderItemModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ShoppingOrderItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ShoppingOrderItem>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ShoppingOrderItem>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByOrderId(long orderId) throws SystemException {
		for (ShoppingOrderItem shoppingOrderItem : findByOrderId(orderId)) {
			remove(shoppingOrderItem);
		}
	}

	public void removeAll() throws SystemException {
		for (ShoppingOrderItem shoppingOrderItem : findAll()) {
			remove(shoppingOrderItem);
		}
	}

	public int countByOrderId(long orderId) throws SystemException {
		Object[] finderArgs = new Object[] { orderId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ORDERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_SHOPPINGORDERITEM_WHERE);

				query.append(_FINDER_COLUMN_ORDERID_ORDERID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORDERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SHOPPINGORDERITEM);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.shopping.model.ShoppingOrderItem")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ShoppingOrderItem>> listenersList = new ArrayList<ModelListener<ShoppingOrderItem>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ShoppingOrderItem>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = ShoppingCartPersistence.class)
	protected ShoppingCartPersistence shoppingCartPersistence;
	@BeanReference(type = ShoppingCategoryPersistence.class)
	protected ShoppingCategoryPersistence shoppingCategoryPersistence;
	@BeanReference(type = ShoppingCouponPersistence.class)
	protected ShoppingCouponPersistence shoppingCouponPersistence;
	@BeanReference(type = ShoppingItemPersistence.class)
	protected ShoppingItemPersistence shoppingItemPersistence;
	@BeanReference(type = ShoppingItemFieldPersistence.class)
	protected ShoppingItemFieldPersistence shoppingItemFieldPersistence;
	@BeanReference(type = ShoppingItemPricePersistence.class)
	protected ShoppingItemPricePersistence shoppingItemPricePersistence;
	@BeanReference(type = ShoppingOrderPersistence.class)
	protected ShoppingOrderPersistence shoppingOrderPersistence;
	@BeanReference(type = ShoppingOrderItemPersistence.class)
	protected ShoppingOrderItemPersistence shoppingOrderItemPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SHOPPINGORDERITEM = "SELECT shoppingOrderItem FROM ShoppingOrderItem shoppingOrderItem";
	private static final String _SQL_SELECT_SHOPPINGORDERITEM_WHERE = "SELECT shoppingOrderItem FROM ShoppingOrderItem shoppingOrderItem WHERE ";
	private static final String _SQL_COUNT_SHOPPINGORDERITEM = "SELECT COUNT(shoppingOrderItem) FROM ShoppingOrderItem shoppingOrderItem";
	private static final String _SQL_COUNT_SHOPPINGORDERITEM_WHERE = "SELECT COUNT(shoppingOrderItem) FROM ShoppingOrderItem shoppingOrderItem WHERE ";
	private static final String _FINDER_COLUMN_ORDERID_ORDERID_2 = "shoppingOrderItem.orderId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "shoppingOrderItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ShoppingOrderItem exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ShoppingOrderItem exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(ShoppingOrderItemPersistenceImpl.class);
}