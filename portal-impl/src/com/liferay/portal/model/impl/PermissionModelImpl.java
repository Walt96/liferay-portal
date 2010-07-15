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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.PermissionModel;
import com.liferay.portal.model.PermissionSoap;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This interface is a model that represents the Permission_ table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PermissionImpl
 * @see       com.liferay.portal.model.Permission
 * @see       com.liferay.portal.model.PermissionModel
 * @generated
 */
public class PermissionModelImpl extends BaseModelImpl<Permission>
	implements PermissionModel {
	public static final String TABLE_NAME = "Permission_";
	public static final Object[][] TABLE_COLUMNS = {
			{ "permissionId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "actionId", new Integer(Types.VARCHAR) },
			{ "resourceId", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table Permission_ (permissionId LONG not null primary key,companyId LONG,actionId VARCHAR(75) null,resourceId LONG)";
	public static final String TABLE_SQL_DROP = "drop table Permission_";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.Permission"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.Permission"),
			true);

	public static Permission toModel(PermissionSoap soapModel) {
		Permission model = new PermissionImpl();

		model.setPermissionId(soapModel.getPermissionId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setActionId(soapModel.getActionId());
		model.setResourceId(soapModel.getResourceId());

		return model;
	}

	public static List<Permission> toModels(PermissionSoap[] soapModels) {
		List<Permission> models = new ArrayList<Permission>(soapModels.length);

		for (PermissionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_GROUPS_PERMISSIONS_NAME = com.liferay.portal.model.impl.GroupModelImpl.MAPPING_TABLE_GROUPS_PERMISSIONS_NAME;
	public static final boolean FINDER_CACHE_ENABLED_GROUPS_PERMISSIONS = com.liferay.portal.model.impl.GroupModelImpl.FINDER_CACHE_ENABLED_GROUPS_PERMISSIONS;
	public static final String MAPPING_TABLE_ROLES_PERMISSIONS_NAME = com.liferay.portal.model.impl.RoleModelImpl.MAPPING_TABLE_ROLES_PERMISSIONS_NAME;
	public static final boolean FINDER_CACHE_ENABLED_ROLES_PERMISSIONS = com.liferay.portal.model.impl.RoleModelImpl.FINDER_CACHE_ENABLED_ROLES_PERMISSIONS;
	public static final String MAPPING_TABLE_USERS_PERMISSIONS_NAME = com.liferay.portal.model.impl.UserModelImpl.MAPPING_TABLE_USERS_PERMISSIONS_NAME;
	public static final boolean FINDER_CACHE_ENABLED_USERS_PERMISSIONS = com.liferay.portal.model.impl.UserModelImpl.FINDER_CACHE_ENABLED_USERS_PERMISSIONS;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.Permission"));

	public PermissionModelImpl() {
	}

	public long getPrimaryKey() {
		return _permissionId;
	}

	public void setPrimaryKey(long pk) {
		setPermissionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_permissionId);
	}

	public long getPermissionId() {
		return _permissionId;
	}

	public void setPermissionId(long permissionId) {
		_permissionId = permissionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getActionId() {
		if (_actionId == null) {
			return StringPool.BLANK;
		}
		else {
			return _actionId;
		}
	}

	public void setActionId(String actionId) {
		_actionId = actionId;

		if (_originalActionId == null) {
			_originalActionId = actionId;
		}
	}

	public String getOriginalActionId() {
		return GetterUtil.getString(_originalActionId);
	}

	public long getResourceId() {
		return _resourceId;
	}

	public void setResourceId(long resourceId) {
		_resourceId = resourceId;

		if (!_setOriginalResourceId) {
			_setOriginalResourceId = true;

			_originalResourceId = resourceId;
		}
	}

	public long getOriginalResourceId() {
		return _originalResourceId;
	}

	public Permission toEscapedModel() {
		if (isEscapedModel()) {
			return (Permission)this;
		}
		else {
			return (Permission)Proxy.newProxyInstance(Permission.class.getClassLoader(),
				new Class[] { Permission.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					Permission.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		PermissionImpl clone = new PermissionImpl();

		clone.setPermissionId(getPermissionId());
		clone.setCompanyId(getCompanyId());
		clone.setActionId(getActionId());
		clone.setResourceId(getResourceId());

		return clone;
	}

	public int compareTo(Permission permission) {
		long pk = permission.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Permission permission = null;

		try {
			permission = (Permission)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = permission.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{permissionId=");
		sb.append(getPermissionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", actionId=");
		sb.append(getActionId());
		sb.append(", resourceId=");
		sb.append(getResourceId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.Permission");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>permissionId</column-name><column-value><![CDATA[");
		sb.append(getPermissionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actionId</column-name><column-value><![CDATA[");
		sb.append(getActionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resourceId</column-name><column-value><![CDATA[");
		sb.append(getResourceId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _permissionId;
	private long _companyId;
	private String _actionId;
	private String _originalActionId;
	private long _resourceId;
	private long _originalResourceId;
	private boolean _setOriginalResourceId;
	private transient ExpandoBridge _expandoBridge;
}