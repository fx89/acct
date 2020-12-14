package com.desolatetimelines.acct.service.dao.model;

import com.desolatetimelines.acct.service.dao.common.CommonAccountingEntity;
import com.desolatetimelines.acct.service.dao.common.Nameable;

public interface Bank extends CommonAccountingEntity<Bank>, Nameable {

	@Override
	default void copyFrom(Bank other) {
		if (other != null) {
			this.setId(other.getId());
			this.setName(other.getName());
		}
	}
}
