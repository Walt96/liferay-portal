/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.asset.list.service.impl;

import com.liferay.asset.list.exception.AssetListEntryTitleException;
import com.liferay.asset.list.exception.NoSuchEntryException;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.service.base.AssetListEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

/**
 * @author Jürgen Kappler
 */
public class AssetListEntryLocalServiceImpl
	extends AssetListEntryLocalServiceBaseImpl {

	@Override
	public AssetListEntry addAssetListEntry(
			long userId, long groupId, String title, int type,
			ServiceContext serviceContext)
		throws PortalException {

		_validate(title);

		User user = userLocalService.getUser(userId);

		long assetListEntryId = counterLocalService.increment();

		AssetListEntry assetListEntry = assetListEntryPersistence.create(
			assetListEntryId);

		assetListEntry.setGroupId(groupId);
		assetListEntry.setCompanyId(user.getCompanyId());
		assetListEntry.setUserId(user.getUserId());
		assetListEntry.setUserName(user.getFullName());
		assetListEntry.setCreateDate(serviceContext.getCreateDate(new Date()));
		assetListEntry.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		assetListEntry.setTitle(title);
		assetListEntry.setType(type);

		return assetListEntryPersistence.update(assetListEntry);
	}

	@Override
	public AssetListEntry deleteAssetListEntry(AssetListEntry assetListEntry)
		throws PortalException {

		assetListEntryLocalService.deleteAssetListEntry(
			assetListEntry.getAssetListEntryId());

		return assetListEntry;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public AssetListEntry deleteAssetListEntry(long assetListEntryId)
		throws PortalException {

		AssetListEntry assetListEntry =
			assetListEntryPersistence.fetchByPrimaryKey(assetListEntryId);

		if (assetListEntry == null) {
			throw new NoSuchEntryException();
		}

		return assetListEntryPersistence.remove(assetListEntryId);
	}

	@Override
	public AssetListEntry updateAssetListEntry(
			long assetListEntryId, String title)
		throws PortalException {

		_validate(title);

		AssetListEntry assetListEntry =
			assetListEntryPersistence.findByPrimaryKey(assetListEntryId);

		assetListEntry.setModifiedDate(new Date());
		assetListEntry.setTitle(title);

		return assetListEntryPersistence.update(assetListEntry);
	}

	private void _validate(String title) throws PortalException {
		if (Validator.isNull(title)) {
			throw new AssetListEntryTitleException();
		}
	}

}