package com.desolatetimelines.acct.catalog.ws.endpoint;

import com.desolatetimelines.acct.catalog.ws.model.BankProperties;
import com.desolatetimelines.acct.catalog.ws.model.BankSaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.BankUUIDResponse;

import java.util.Collection;

/**
 * Defines operations that are supported by the Catalog service for banks
 */
public interface BanksEndpoint {

    /**
     * Saves the properties in the given request for the bank with the given bank UUID.
     * If a bank UUID is not given then a new bank is created with the given properties.
     * If a bank UUID is given and there is no bank defined with that UUID, an exception
     * is thrown. In case of constraint violation exceptions, an exception is thrown.
     *
     * @param bankUUID the given bank UUID
     * @param request  the given request
     * @return a wrapping object for the UUID of the persisted entity
     */
    BankUUIDResponse saveBank(String bankUUID, BankSaveRequest request);

    /**
     * Returns a collection of objects that represent all the banks registered in ACCT.
     */
    Collection<BankProperties> getBanks();

    /**
     * Deletes the banks referenced by the UUIDs in the given  collection of bank UUIDs.<br />
     * <br />
     * Throws an exception if any of the referenced banks are in use.<br />
     * <br />
     * Throws an exception if any of the referenced banks cannot be found.
     *
     * @param bankUUIDs the given collection of bank UUIDs
     */
    void deleteBanks(Collection<String> bankUUIDs);

}
