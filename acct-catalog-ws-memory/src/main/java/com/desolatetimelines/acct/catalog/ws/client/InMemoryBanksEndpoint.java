package com.desolatetimelines.acct.catalog.ws.client;

import com.desolatetimelines.acct.catalog.ws.endpoint.BanksEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.BankProperties;
import com.desolatetimelines.acct.catalog.ws.model.BankSaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.BankUUIDResponse;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBanksEndpoint implements BanksEndpoint {

    private final Map<String, BankProperties> banksByUUID = new ConcurrentHashMap<>();

    @Override
    public BankUUIDResponse saveBank(String bankUUID, BankSaveRequest request) {
        final String uuid = Optional.ofNullable(bankUUID).orElseGet(() -> UUID.randomUUID().toString());

        banksByUUID.put(
            uuid,
            BankProperties.builder()
                .withBankUUID(uuid)
                .withBankCode(request.bankCode())
                .withBankName(request.bankName())
                .withBankIconUUID(request.bankIconUUID())
                .withInternetBankingURL(request.internetBankingURL())
                .build()
        );

        return new BankUUIDResponse(uuid);
    }

    @Override
    public Collection<BankProperties> getBanks() {
        return banksByUUID.values();
    }

    @Override
    public void deleteBanks(Collection<String> bankUUIDs) {
        bankUUIDs.forEach(banksByUUID::remove);
    }

}
