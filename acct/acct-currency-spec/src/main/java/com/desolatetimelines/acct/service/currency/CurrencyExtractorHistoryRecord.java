package com.desolatetimelines.acct.service.currency;

import java.io.Serializable;
import java.util.Date;

public interface CurrencyExtractorHistoryRecord extends Serializable {
	Date getDate();

	void setDate(Date date);

	Double getValue();

	void setValue(Double value);
}
