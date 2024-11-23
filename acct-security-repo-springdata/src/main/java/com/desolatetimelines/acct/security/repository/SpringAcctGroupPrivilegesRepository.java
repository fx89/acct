package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctGroupPrivilege;
import com.desolatetimelines.acct.security.model.JpaAcctGroupPrivilege;
import com.desolatetimelines.acct.security.springrepository.JpaGroupPrivilegesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.security.util.AcctSecurityRepoSpringdataUtils.doWithJJpaAcctGroupPrivilege;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctGroupPrivilegesRepository} that uses Spring Data as back-end
 */
@Service
public class SpringAcctGroupPrivilegesRepository implements AcctGroupPrivilegesRepository {

    private final JpaGroupPrivilegesRepository groupPrivilegesRepository;

    public SpringAcctGroupPrivilegesRepository(JpaGroupPrivilegesRepository groupPrivilegesRepository) {
        this.groupPrivilegesRepository = groupPrivilegesRepository;
    }

    @Override
    public AcctGroupPrivilege createNew() {
        return new JpaAcctGroupPrivilege();
    }

    @Override
    public Set<AcctGroupPrivilege> findAllByGroupUUIDIn(Collection<String> groupUUIDs) {
        return
            groupPrivilegesRepository.findAllByGroupUUIDIn(groupUUIDs)
                .stream()
                .map(acctGroupPrivilege -> doWithJJpaAcctGroupPrivilege(acctGroupPrivilege, identity()))
                .collect(Collectors.toSet());
    }
}
