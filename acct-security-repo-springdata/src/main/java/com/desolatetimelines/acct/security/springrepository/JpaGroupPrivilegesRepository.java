package com.desolatetimelines.acct.security.springrepository;

import com.desolatetimelines.acct.security.model.JpaAcctGroupPrivilege;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface JpaGroupPrivilegesRepository extends CrudRepository<JpaAcctGroupPrivilege, Long> {

    Set<JpaAcctGroupPrivilege> findAllByGroupUUIDIn(Collection<String> groupUUIDs);

    void deleteByGroupUUIDAndPrivilegeNameIn(String groupUUID, Collection<String> privilegeNames);

}
