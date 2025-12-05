package com.desolatetimelines.acct.reporting.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"data_provider_instance_property\"")
public class JpaAcctDataProviderInstanceProperty implements AcctDataProviderInstanceProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataProviderInstancePropertyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_provider_instance_id", nullable = false)
    private JpaAcctDataProviderInstance dataProviderInstance;

    @Column(name = "property_name", nullable = false)
    private String propertyName;

    @Column(name = "property_value", nullable = false)
    private String propertyValue;

    public Long getDataProviderInstancePropertyId() {
        return dataProviderInstancePropertyId;
    }

    public void setDataProviderInstancePropertyId(Long dataProviderInstancePropertyId) {
        this.dataProviderInstancePropertyId = dataProviderInstancePropertyId;
    }

    public void setDataProviderInstance(JpaAcctDataProviderInstance dataProviderInstance) {
        this.dataProviderInstance = dataProviderInstance;
    }

    @Override
    public AcctDataProviderInstance getDataProviderInstance() {
        return dataProviderInstance;
    }

    @Override
    public void setDataProviderInstance(AcctDataProviderInstance instance) {
        this.dataProviderInstance = (JpaAcctDataProviderInstance) instance;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String getPropertyValue() {
        return propertyValue;
    }

    @Override
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctDataProviderInstanceProperty that = (JpaAcctDataProviderInstanceProperty) o;
        return Objects.equals(dataProviderInstance, that.dataProviderInstance) && Objects.equals(propertyName, that.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataProviderInstance, propertyName);
    }

}
