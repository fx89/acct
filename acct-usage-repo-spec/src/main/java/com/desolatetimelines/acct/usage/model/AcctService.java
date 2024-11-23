package com.desolatetimelines.acct.usage.model;

/**
 * Represents a service that's registered with the ACCT usage service
 */
public interface AcctService {
    String getServiceName();

    void setServiceName(String serviceName);

    String getServiceContextPath();

    void setServiceContextPath(String serviceContextPath);

}
