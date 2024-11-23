package com.desolatetimelines.acct.usage.ws.model;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Objects.requireNonNull;

/**
 * Defines a list of item type names that are in use for a given service
 *
 * @param serviceName        the name of the given service
 * @param serviceContextPath the context path of the given service (i.e. /service/security/v1)
 * @param itemTypes          the list of item type names
 */
public record ServiceItemTypesList(
    String serviceName,
    String serviceContextPath,
    Collection<String> itemTypes
) {

    public static ServiceItemTypesListBuilder builder() {
        return new ServiceItemTypesListBuilder();
    }

    /**
     * {@code ServiceItemTypesList} builder static inner class.
     */
    public static final class ServiceItemTypesListBuilder {
        private String serviceName;
        private String serviceContextPath;
        private final Collection<String> itemTypes = new HashSet<>();

        private ServiceItemTypesListBuilder() {
        }

        /**
         * Sets the {@code serviceName} and returns a reference to this Builder enabling method chaining.
         *
         * @param serviceName the {@code serviceName} to set
         * @return a reference to this Builder
         */
        public ServiceItemTypesListBuilder withServiceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        /**
         * Sets the {@code serviceContextPath} and returns a reference to this Builder enabling method chaining.
         *
         * @param serviceContextPath the {@code serviceContextPath} to set
         * @return a reference to this Builder
         */
        public ServiceItemTypesListBuilder withServiceContextPath(String serviceContextPath) {
            this.serviceContextPath = serviceContextPath;
            return this;
        }

        /**
         * Adds an {@code itemType} and returns a reference to this Builder enabling method chaining.
         *
         * @param itemType the {@code itemType} to add
         * @return a reference to this Builder
         */
        public ServiceItemTypesListBuilder withItemType(String itemType) {
            itemTypes.add(itemType);
            return this;
        }

        /**
         * Adds {@code itemTypes} and returns a reference to this Builder enabling method chaining.
         *
         * @param itemTypes the {@code itemTypes} to add
         * @return a reference to this Builder
         */
        public ServiceItemTypesListBuilder withItemType(Collection<String> itemTypes) {
            this.itemTypes.addAll(itemTypes);
            return this;
        }

        /**
         * Returns a {@code ServiceItemTypesList} built from the parameters previously set.
         *
         * @return a {@code ServiceItemTypesList} built with parameters of this {@code ServiceItemTypesList.Builder}
         */
        public ServiceItemTypesList build() {
            requireNonNull(serviceName, "Service name not provided");
            requireNonNull(serviceContextPath, "Service context path not provided");

            if (itemTypes.isEmpty()) {
                throw new IllegalArgumentException("Item types not provided");
            }

            return new ServiceItemTypesList(serviceName, serviceContextPath, itemTypes);
        }
    }
}
