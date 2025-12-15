package com.desolatetimelines.acct.reporting.model;

public interface AcctReportDataProviderInstance {

    AcctReport getReport();

    void setReport(AcctReport report);

    AcctDataProviderInstance getDataProviderInstance();

    void setDataProviderInstance(AcctDataProviderInstance dataProviderInstance);

}
