package com.desolatetimelines.acct.reporting.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"data_provider_instance\"")
public class JpaAcctDataProviderInstance implements AcctDataProviderInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataProviderInstanceId;

    @Column(name = "data_provider_instance_uuid", unique = true, nullable = false)
    private String dataProviderInstanceUUID;

    @Column(name = "data_provider_instance_name", nullable = false)
    private String dataProviderInstanceName;

    @Column(name = "data_provider_uuid", nullable = false)
    private String dataProviderUUID;

    public Long getDataProviderInstanceId() {
        return dataProviderInstanceId;
    }

    public void setDataProviderInstanceId(Long dataProviderInstanceId) {
        this.dataProviderInstanceId = dataProviderInstanceId;
    }

    @Override
    public String getDataProviderInstanceUUID() {
        return dataProviderInstanceUUID;
    }

    @Override
    public void setDataProviderInstanceUUID(String dataProviderInstanceUUID) {
        this.dataProviderInstanceUUID = dataProviderInstanceUUID;
    }

    @Override
    public String getDataProviderInstanceName() {
        return dataProviderInstanceName;
    }

    @Override
    public void setDataProviderInstanceName(String dataProviderInstanceName) {
        this.dataProviderInstanceName = dataProviderInstanceName;
    }

    @Override
    public String getDataProviderUUID() {
        return dataProviderUUID;
    }

    @Override
    public void setDataProviderUUID(String dataProviderUUID) {
        this.dataProviderUUID = dataProviderUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctDataProviderInstance that = (JpaAcctDataProviderInstance) o;
        return Objects.equals(dataProviderInstanceUUID, that.dataProviderInstanceUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dataProviderInstanceUUID);
    }

}
