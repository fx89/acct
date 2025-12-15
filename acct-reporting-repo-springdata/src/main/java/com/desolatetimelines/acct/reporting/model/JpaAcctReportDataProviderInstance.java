package com.desolatetimelines.acct.reporting.model;

import jakarta.persistence.*;

import java.util.Objects;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.doWithJpaAcctDataProviderInstance;
import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.doWithJpaAcctReport;

@Entity
@Table(name = "\"report_data_provider_instance\"")
public class JpaAcctReportDataProviderInstance implements AcctReportDataProviderInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportDataProviderInstanceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "report_id", nullable = false)
    private JpaAcctReport report;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "data_provider_instance_id", nullable = false)
    private JpaAcctDataProviderInstance dataProviderInstance;

    public Long getReportDataProviderInstanceId() {
        return reportDataProviderInstanceId;
    }

    public void setReportDataProviderInstanceId(Long reportDataProviderInstanceId) {
        this.reportDataProviderInstanceId = reportDataProviderInstanceId;
    }

    @Override
    public AcctReport getReport() {
        return report;
    }

    @Override
    public void setReport(AcctReport report) {
        doWithJpaAcctReport(report, this::setReport);
    }

    public void setReport(JpaAcctReport report) {
        this.report = report;
    }

    @Override
    public AcctDataProviderInstance getDataProviderInstance() {
        return dataProviderInstance;
    }

    @Override
    public void setDataProviderInstance(AcctDataProviderInstance dataProviderInstance) {
        doWithJpaAcctDataProviderInstance(dataProviderInstance, this::setDataProviderInstance);
    }

    public void setDataProviderInstance(JpaAcctDataProviderInstance dataProviderInstance) {
        this.dataProviderInstance = dataProviderInstance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctReportDataProviderInstance that = (JpaAcctReportDataProviderInstance) o;
        return Objects.equals(report, that.report) && Objects.equals(dataProviderInstance, that.dataProviderInstance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(report, dataProviderInstance);
    }
}
