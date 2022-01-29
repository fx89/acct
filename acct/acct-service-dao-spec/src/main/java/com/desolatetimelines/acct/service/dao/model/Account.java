package com.desolatetimelines.acct.service.dao.model;

import com.desolatetimelines.acct.service.dao.common.CommonAccountingEntity;
import com.desolatetimelines.acct.service.dao.common.Nameable;

public interface Account extends CommonAccountingEntity<Account>, Nameable {

	void setId(Long id);



	@Override
	default void copyFrom(Account object) {
		if (object != null) {
			this.setId(object.getId());
			this.setName(object.getName());
		}
	}
}
