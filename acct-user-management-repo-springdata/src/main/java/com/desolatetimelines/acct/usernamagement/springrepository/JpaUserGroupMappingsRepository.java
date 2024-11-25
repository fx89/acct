package com.desolatetimelines.acct.usernamagement.springrepository;

import com.desolatetimelines.acct.usernamagement.model.JpaAcctUserGroupMapping;
import org.springframework.data.repository.CrudRepository;

public interface JpaUserGroupMappingsRepository extends CrudRepository<JpaAcctUserGroupMapping, Long> {

    void deleteByUserUserUUIDAndGroupGroupUUID(String userUUID, String groupUUID);

}
