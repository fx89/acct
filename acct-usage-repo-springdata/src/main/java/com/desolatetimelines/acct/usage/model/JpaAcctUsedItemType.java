package com.desolatetimelines.acct.usage.model;

import jakarta.persistence.*;

import java.util.Objects;

import static com.desolatetimelines.acct.usage.util.AcctUsageRepoSpringdataUtils.doWithJpaAcctService;

@Entity
@Table(name = "used_item_type")
public class JpaAcctUsedItemType implements AcctUsedItemType {

    @Id
    @Column(name = "used_item_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usedItemTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private JpaAcctService service;

    @Column(name = "used_item_typeName")
    private String usedItemTypeName;

    public JpaAcctUsedItemType() {
    }

    public JpaAcctUsedItemType(Long usedItemId, JpaAcctService service, String usedItemTypeName) {
        this.usedItemTypeId = usedItemId;
        this.service = service;
        this.usedItemTypeName = usedItemTypeName;
    }

    private JpaAcctUsedItemType(JpaAcctUsedItemTypeBuilder builder) {
        setUsedItemTypeId(builder.usedItemId);
        setService(builder.service);
        setUsedItemTypeName(builder.usedItemTypeName);
    }

    public static JpaAcctUsedItemTypeBuilder builder() {
        return new JpaAcctUsedItemTypeBuilder();
    }

    public Long getUsedItemTypeId() {
        return usedItemTypeId;
    }

    public void setUsedItemTypeId(Long usedItemTypeId) {
        this.usedItemTypeId = usedItemTypeId;
    }

    @Override
    public JpaAcctService getService() {
        return service;
    }

    @Override
    public void setService(AcctService service) {
        doWithJpaAcctService(service, acctService -> this.service = acctService);
    }

    @Override
    public String getUsedItemTypeName() {
        return usedItemTypeName;
    }

    @Override
    public void setUsedItemTypeName(String usedItemTypeName) {
        this.usedItemTypeName = usedItemTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctUsedItemType that = (JpaAcctUsedItemType) o;
        return Objects.equals(usedItemTypeId, that.usedItemTypeId) && Objects.equals(service, that.service) && Objects.equals(usedItemTypeName, that.usedItemTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usedItemTypeId, service, usedItemTypeName);
    }


    /**
     * {@code UsedItemType} builder static inner class.
     */
    public static final class JpaAcctUsedItemTypeBuilder {
        private Long usedItemId;
        private JpaAcctService service;
        private String usedItemTypeName;

        private JpaAcctUsedItemTypeBuilder() {
        }

        /**
         * Sets the {@code usedItemId} and returns a reference to this Builder enabling method chaining.
         *
         * @param usedItemId the {@code usedItemId} to set
         * @return a reference to this Builder
         */
        public JpaAcctUsedItemTypeBuilder withUsedItemId(Long usedItemId) {
            this.usedItemId = usedItemId;
            return this;
        }

        /**
         * Sets the {@code service} and returns a reference to this Builder enabling method chaining.
         *
         * @param service the {@code service} to set
         * @return a reference to this Builder
         */
        public JpaAcctUsedItemTypeBuilder withService(JpaAcctService service) {
            this.service = service;
            return this;
        }

        /**
         * Sets the {@code usedItemTypeName} and returns a reference to this Builder enabling method chaining.
         *
         * @param usedItemTypeName the {@code usedItemTypeName} to set
         * @return a reference to this Builder
         */
        public JpaAcctUsedItemTypeBuilder withUsedItemTypeName(String usedItemTypeName) {
            this.usedItemTypeName = usedItemTypeName;
            return this;
        }

        /**
         * Returns a {@code UsedItemType} built from the parameters previously set.
         *
         * @return a {@code UsedItemType} built with parameters of this {@code UsedItemType.Builder}
         */
        public JpaAcctUsedItemType build() {
            return new JpaAcctUsedItemType(this);
        }
    }
}
