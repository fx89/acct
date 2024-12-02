package com.desolatetimelines.acct.security.data.service;

import com.desolatetimelines.acct.security.data.exception.AcctSecurityDataServiceUnsupportedResourceException;
import com.desolatetimelines.acct.security.model.*;
import com.desolatetimelines.acct.security.repository.AcctDashboardOwnersRepository;
import com.desolatetimelines.acct.security.repository.AcctGroupPrivilegesRepository;
import com.desolatetimelines.acct.security.repository.AcctReportOwnersRepository;
import com.desolatetimelines.acct.security.repository.AcctWorkspaceOwnersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Facade for the data layer of the security service, allowing for the
 * loading and persisting of security data objects
 */
@Service
public class AcctSecurityDataService {

    private final AcctGroupPrivilegesRepository groupPrivilegesRepository;

    private final Map<Class<? extends AcctResourceOwner>, AcctSecurityResourceOwnersDataService<?>>
        resourceOwnerDataServices;

    public AcctSecurityDataService(
        AcctGroupPrivilegesRepository groupPrivilegesRepository,
        AcctWorkspaceOwnersRepository workspaceOwnersRepository,
        AcctDashboardOwnersRepository dashboardOwnersRepository,
        AcctReportOwnersRepository reportOwnersRepository
    ) {
        this.groupPrivilegesRepository = groupPrivilegesRepository;

        resourceOwnerDataServices =
            Map.of(
                AcctWorkspaceOwner.class,
                new AcctSecurityResourceOwnersDataService<>(workspaceOwnersRepository),

                AcctDashboardOwner.class,
                new AcctSecurityResourceOwnersDataService<>(dashboardOwnersRepository),

                AcctReportOwner.class,
                new AcctSecurityResourceOwnersDataService<>(reportOwnersRepository)
            );

    }

    /**
     * Retrieves all {@link AcctGroupPrivilege group privileges} mapped to the groups with the given UUIDs
     *
     * @param groupUUIDs the given UUIDs
     */
    public Set<AcctGroupPrivilege> findAllGroupPrivilegesByGroupUUIDIn(Collection<String> groupUUIDs) {
        return groupPrivilegesRepository.findAllByGroupUUIDIn(groupUUIDs);
    }

    /**
     * Creates an ACCT group / privilege mapping using the given group UUID and privilege ID
     *
     * @param groupUUID   the given group UUID
     * @param privilegeId the given privilege ID
     * @return a reference to the newly created ACCT group / privilege mapping
     */
    public AcctGroupPrivilege createAcctGroupPrivilege(String groupUUID, String privilegeId) {
        // Create a new entity
        final AcctGroupPrivilege newAcctGroupPrivilege = groupPrivilegesRepository.createNew();

        // Set the properties
        newAcctGroupPrivilege.setGroupUUID(groupUUID);
        newAcctGroupPrivilege.setPrivilegeName(privilegeId);

        // Persist
        return groupPrivilegesRepository.save(newAcctGroupPrivilege);
    }

    /**
     * Deletes the group / privilege mappings for the given group and privileges
     */
    public void deleteGroupPrivilegeMappings(String groupUUID, Collection<String> privilegeIDs) {
        groupPrivilegesRepository.deleteAllByGroupUUIDAndPrivilegeNameIn(groupUUID, privilegeIDs);
    }

    /**
     * Returns a reference to the data service that manages the resource owner
     * records of the given type
     *
     * @param resourceOwnerType the given type
     */
    @SuppressWarnings("unchecked")
    public <T extends AcctResourceOwner> AcctSecurityResourceOwnersDataService<T> getResourceOwnerDataService(
        Class<T> resourceOwnerType
    ) {
        return
            (AcctSecurityResourceOwnersDataService<T>)
                Optional
                    .ofNullable(resourceOwnerDataServices.get(resourceOwnerType))
                    .orElseThrow(() -> new AcctSecurityDataServiceUnsupportedResourceException(
                        "Resources of type [" + resourceOwnerType.getCanonicalName() + "] are not supported"
                    ));
    }


}
