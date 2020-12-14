package com.desolatetimelines.acct.service.dao.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonAccountingEntityRepository<T extends CommonAccountingEntity<T>> extends ConcurrentHashMap<Long, T>
		implements Map<Long, T> {

	public T save(T object) {
		if (object == null) {
			return null;
		}

		Long existingId = object.getId();
		T existingObject = (existingId == null ? null : this.get(existingId));

		if (existingObject == null) {
			existingObject = object;
			Long id = this.keySet().stream().mapToLong(k -> k == null ? 0L : k).max().orElse(0L) + 1L;
			existingObject.setId(id);
		} else {
			existingObject.copyFrom(object);
		}

		this.put(existingObject.getId(), existingObject);

		return existingObject;
	}

}
