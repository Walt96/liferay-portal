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

package com.liferay.portlet.social.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * <p>
 * This interface is a model that represents the SocialRequest table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SocialRequest
 * @see       com.liferay.portlet.social.model.impl.SocialRequestImpl
 * @see       com.liferay.portlet.social.model.impl.SocialRequestModelImpl
 * @generated
 */
public interface SocialRequestModel extends BaseModel<SocialRequest> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	@AutoEscape
	public String getUuid();

	public void setUuid(String uuid);

	public long getRequestId();

	public void setRequestId(long requestId);

	public long getGroupId();

	public void setGroupId(long groupId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public long getUserId();

	public void setUserId(long userId);

	public String getUserUuid() throws SystemException;

	public void setUserUuid(String userUuid);

	public long getCreateDate();

	public void setCreateDate(long createDate);

	public long getModifiedDate();

	public void setModifiedDate(long modifiedDate);

	public String getClassName();

	public long getClassNameId();

	public void setClassNameId(long classNameId);

	public long getClassPK();

	public void setClassPK(long classPK);

	public int getType();

	public void setType(int type);

	@AutoEscape
	public String getExtraData();

	public void setExtraData(String extraData);

	public long getReceiverUserId();

	public void setReceiverUserId(long receiverUserId);

	public String getReceiverUserUuid() throws SystemException;

	public void setReceiverUserUuid(String receiverUserUuid);

	public int getStatus();

	public void setStatus(int status);

	public SocialRequest toEscapedModel();

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(SocialRequest socialRequest);

	public int hashCode();

	public String toString();

	public String toXmlString();
}