package com.desolatetimelines.acct.usernamagement.springrepository;

import com.desolatetimelines.acct.usernamagement.model.JpaAcctUserGroupMapping;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JpaUserGroupMappingsRepository extends CrudRepository<JpaAcctUserGroupMapping, Long> {

    void deleteByUserUserUUIDAndGroupGroupUUID(String userUUID, String groupUUID);

    Set<JpaAcctUserGroupMapping> findAllByUserUserUUID(String userUUID);

}
