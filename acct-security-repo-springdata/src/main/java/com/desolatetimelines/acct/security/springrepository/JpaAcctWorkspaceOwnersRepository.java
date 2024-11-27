package com.desolatetimelines.acct.security.springrepository;

import com.desolatetimelines.acct.security.model.JpaAcctWorkspaceOwner;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface JpaAcctWorkspaceOwnersRepository extends CrudRepository<JpaAcctWorkspaceOwner, Long> {

    Set<JpaAcctWorkspaceOwner> findAllByWorkspaceUUIDIn(Collection<String> workspaceUUIDs);

}
