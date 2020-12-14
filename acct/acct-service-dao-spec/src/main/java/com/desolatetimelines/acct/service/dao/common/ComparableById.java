package com.desolatetimelines.acct.service.dao.common;

public interface ComparableById extends Comparable<ComparableById>, Identifiable {

	@Override
	default int compareTo(ComparableById other) {
		if (other == null) {
			return 1;
		}

		if (this.getId() == null) {
			return -1;
		}

		if (other.getId() == null) {
			return 1;
		}

		return this.getId().compareTo(other.getId());
	}
}
