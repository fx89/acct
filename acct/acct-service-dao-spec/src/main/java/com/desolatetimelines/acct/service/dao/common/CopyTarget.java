package com.desolatetimelines.acct.service.dao.common;

public interface CopyTarget<T> {
	void copyFrom(T object);
}
