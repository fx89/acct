package com.desolatetimelines.acct.authorization.data.service;

import com.desolatetimelines.acct.authorization.data.model.AcctUser;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Mock implementation of the {@link AcctAuthorizationServerDataService} interface
 */
@Service
@Profile("acctAuthServerDataMock")
public class MockAcctAuthorizationServerDataService implements AcctAuthorizationServerDataService {
    @Override
    public Optional<AcctUser> getUserByLoginName(String loginName) {
        return
            Optional.of(
                AcctUser.builder()
                    .withUserUUID("f2583a41-18c5-4b26-8f1f-550bdc9e9002")
                    .withGrantedAuthority("TEST_PRIVILEGE_1")
                    .withGrantedAuthority("TEST_PRIVILEGE_2")
                    .withGrantedAuthority("TEST_PRIVILEGE_3")
                    .withGrantedAuthority("TEST_PRIVILEGE_4")
                    .withGrantedAuthority("TEST_PRIVILEGE_5")
                    .withUsername(loginName)
                    .withPassword("{bcrypt}$2a$10$KQJy1s17cDYbxKraF18mEe97pzJcqxRxlXp18QEqiZIkgGyN6rpxS") // 12345
                    .withUserHumanReadableName("Test User")
                    .withDefaultWorkspaceUUID("09e003b2-f4e9-47f4-950b-eb851760b0bb")
                    .withUserIconUUID("1388f720-7d2c-4d19-b139-ab1430172c98")
                    .build()
            );
    }
}
