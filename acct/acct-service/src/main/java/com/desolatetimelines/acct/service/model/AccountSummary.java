package com.desolatetimelines.acct.service.model;

import java.io.Serializable;

public class AccountSummary implements Serializable {
	private Double runningSumIncoming;

	private Double runningSumOutgoing;

	public Double getRunningSumIncoming() {
		return runningSumIncoming;
	}

	public void setRunningSumIncoming(Double runningSumIncoming) {
		this.runningSumIncoming = runningSumIncoming;
	}

	public Double getRunningSumOutgoing() {
		return runningSumOutgoing;
	}

	public void setRunningSumOutgoing(Double runningSumOutgoing) {
		this.runningSumOutgoing = runningSumOutgoing;
	}

}
