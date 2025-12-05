package com.desolatetimelines.acct.reporting.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"data_provider_instance_runtime_parameter\"")
public class JpaAcctDataProviderInstanceRuntimeParameter implements AcctDataProviderInstanceRuntimeParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataProviderInstanceRuntimeParameterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_provider_instance_id", nullable = false)
    private JpaAcctDataProviderInstance dataProviderInstance;

    @Column(name = "parameter_name", nullable = false)
    private String parameterName;

    @Column(name = "parameter_default_value")
    private String parameterDefaultValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "parameter_data_type", nullable = false)
    private AcctDataProviderInstanceRuntimeParameterDataType parameterDataType;

    @Column(name = "mandatory", nullable = false)
    private Boolean mandatory;

    public Long getDataProviderInstanceRuntimeParameterId() {
        return dataProviderInstanceRuntimeParameterId;
    }

    public void setDataProviderInstanceRuntimeParameterId(Long dataProviderInstanceRuntimeParameterId) {
        this.dataProviderInstanceRuntimeParameterId = dataProviderInstanceRuntimeParameterId;
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
    public String getParameterName() {
        return parameterName;
    }

    @Override
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String getParameterDefaultValue() {
        return parameterDefaultValue;
    }

    @Override
    public void setParameterDefaultValue(String defaultValue) {
        this.parameterDefaultValue = defaultValue;
    }

    @Override
    public AcctDataProviderInstanceRuntimeParameterDataType getParameterDataType() {
        return parameterDataType;
    }

    @Override
    public void setParameterDataType(AcctDataProviderInstanceRuntimeParameterDataType dataType) {
        this.parameterDataType = dataType;
    }

    @Override
    public Boolean isMandatory() {
        return mandatory;
    }

    @Override
    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctDataProviderInstanceRuntimeParameter that = (JpaAcctDataProviderInstanceRuntimeParameter) o;
        return Objects.equals(dataProviderInstance, that.dataProviderInstance) && Objects.equals(parameterName, that.parameterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataProviderInstance, parameterName);
    }

}
