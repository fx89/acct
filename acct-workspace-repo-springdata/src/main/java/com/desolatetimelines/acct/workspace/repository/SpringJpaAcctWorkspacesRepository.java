package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.model.JpaAcctWorkspace;
import com.desolatetimelines.acct.workspace.springrepository.JpaAcctWorkspacesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctWorkspace;
import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctWorkspaceReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctWorkspacesRepository} using Spring Data JPA
 */
@Service
public class SpringJpaAcctWorkspacesRepository implements AcctWorkspacesRepository {

    private final JpaAcctWorkspacesRepository jpaAcctWorkspacesRepository;

    public SpringJpaAcctWorkspacesRepository(JpaAcctWorkspacesRepository jpaAcctWorkspacesRepository) {
        this.jpaAcctWorkspacesRepository = jpaAcctWorkspacesRepository;
    }

    @Override
    public AcctWorkspace createNew() {
        return new JpaAcctWorkspace();
    }

    @Override
    public AcctWorkspace save(AcctWorkspace workspace) {
        return doWithJpaAcctWorkspaceReturning(workspace, jpaAcctWorkspacesRepository::save);
    }

    @Override
    public Optional<AcctWorkspace> findFirstByWorkspaceUUID(String workspaceUUID) {
        return jpaAcctWorkspacesRepository.findFirstByWorkspaceUUID(workspaceUUID).map(identity());
    }

    @Override
    public void delete(AcctWorkspace workspace) {
        doWithJpaAcctWorkspace(workspace, jpaAcctWorkspacesRepository::delete);
    }


}
