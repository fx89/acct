package com.desolatetimelines.acct.usernamagement.repository;

import com.desolatetimelines.acct.usermanagement.model.AcctUserGroupMapping;
import com.desolatetimelines.acct.usermanagement.repository.AcctUserGroupMappingsRepository;
import com.desolatetimelines.acct.usernamagement.model.JpaAcctUserGroupMapping;
import com.desolatetimelines.acct.usernamagement.springrepository.JpaUserGroupMappingsRepository;
import org.springframework.stereotype.Service;

import static com.desolatetimelines.acct.usernamagement.util.AcctUserManagementRepoSpringDataUtils.doWithJpaAcctUserGroupMapping;

/**
 * Implementation of the {@link AcctUserGroupMappingsRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctUserGroupMappingsRepository implements AcctUserGroupMappingsRepository {

    private final JpaUserGroupMappingsRepository userGroupMappingsRepository;

    public SpringJpaAcctUserGroupMappingsRepository(JpaUserGroupMappingsRepository userGroupMappingsRepository) {
        this.userGroupMappingsRepository = userGroupMappingsRepository;
    }

    @Override
    public AcctUserGroupMapping createNew() {
        return new JpaAcctUserGroupMapping();
    }

    @Override
    public AcctUserGroupMapping save(AcctUserGroupMapping acctUserGroupMapping) {
        return doWithJpaAcctUserGroupMapping(acctUserGroupMapping, userGroupMappingsRepository::save);
    }

    @Override
    public void deleteByUserUUIDAndGroupUUID(String userUUID, String groupUUID) {
        userGroupMappingsRepository.deleteByUserUserUUIDAndGroupGroupUUID(userUUID, groupUUID);
    }
}
