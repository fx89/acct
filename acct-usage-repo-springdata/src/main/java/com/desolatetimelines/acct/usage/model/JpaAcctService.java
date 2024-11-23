package com.desolatetimelines.acct.usage.model;

import jakarta.persistence.*;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "service")
public class JpaAcctService implements AcctService {

    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_context_path")
    private String serviceContextPath;

    public JpaAcctService() {
    }

    public JpaAcctService(Long serviceId, String serviceName, String serviceContextPath) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceContextPath = serviceContextPath;
    }

    private JpaAcctService(JpaAcctServiceBuilder builder) {
        setServiceId(builder.serviceId);
        setServiceName(builder.serviceName);
        setServiceContextPath(builder.serviceContextPath);
    }

    public static JpaAcctServiceBuilder builder() {
        return new JpaAcctServiceBuilder();
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getServiceContextPath() {
        return serviceContextPath;
    }

    @Override
    public void setServiceContextPath(String serviceContextPath) {
        this.serviceContextPath = serviceContextPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctService service = (JpaAcctService) o;
        return Objects.equals(serviceId, service.serviceId) && Objects.equals(serviceName, service.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, serviceName);
    }


    /**
     * {@code Service} builder static inner class.
     */
    public static final class JpaAcctServiceBuilder {
        private Long serviceId;
        private String serviceName;
        private String serviceContextPath;

        private JpaAcctServiceBuilder() {
        }

        /**
         * Sets the {@code serviceId} and returns a reference to this Builder enabling method chaining.
         *
         * @param serviceId the {@code serviceId} to set
         * @return a reference to this Builder
         */
        public JpaAcctServiceBuilder withServiceId(Long serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        /**
         * Sets the {@code serviceName} and returns a reference to this Builder enabling method chaining.
         *
         * @param serviceName the {@code serviceName} to set
         * @return a reference to this Builder
         */
        public JpaAcctServiceBuilder withServiceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        /**
         * Sets the {@code serviceContextPath} and returns a reference to this Builder enabling method chaining.
         *
         * @param serviceContextPath the {@code serviceContextPath} to set
         * @return a reference to this Builder
         */
        public JpaAcctServiceBuilder withServiceContextPath(String serviceContextPath) {
            this.serviceContextPath = serviceContextPath;
            return this;
        }

        /**
         * Returns a {@code Service} built from the parameters previously set.
         *
         * @return a {@code Service} built with parameters of this {@code Service.Builder}
         */
        public JpaAcctService build() {
            requireNonNull(serviceName, "Service name not provided");
            requireNonNull(serviceContextPath, "Service context path not provided");

            return new JpaAcctService(this);
        }
    }
}
