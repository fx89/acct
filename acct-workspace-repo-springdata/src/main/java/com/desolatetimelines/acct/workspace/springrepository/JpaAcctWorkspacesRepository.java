package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.JpaAcctWorkspace;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctWorkspacesRepository extends CrudRepository<JpaAcctWorkspace, Long> {

    Optional<JpaAcctWorkspace> findFirstByWorkspaceUUID(String workspaceUUID);

}
