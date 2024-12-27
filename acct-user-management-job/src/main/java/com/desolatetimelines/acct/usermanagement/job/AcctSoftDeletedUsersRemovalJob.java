package com.desolatetimelines.acct.usermanagement.job;

import com.desolatetimelines.acct.common.utils.SpringCronExpressionBuilder;
import com.desolatetimelines.acct.job.framework.model.AcctJobCron;
import com.desolatetimelines.acct.job.framework.service.AcctJob;
import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.service.AcctUserManagementService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * This background job is responsible for removing soft-deleted users from the database.
 * Removal occurs after a given amount of time has passed since the user was soft-deleted.
 */
@Service
public class AcctSoftDeletedUsersRemovalJob extends AcctJob {

    private final AcctUserManagementService userManagementService;

    private final int softDeletedUsersExpiryPeriodInDays;

    public AcctSoftDeletedUsersRemovalJob(
        AcctUserManagementService userManagementService,
        @Value("${USER_MANAGEMENT_SOFT_DELETED_USERS_EXPIRY_PERIOD_DAYS}") int softDeletedUsersExpiryPeriodInDays
    ) {
        this.userManagementService = userManagementService;
        this.softDeletedUsersExpiryPeriodInDays = softDeletedUsersExpiryPeriodInDays;
    }

    @Override
    protected String getJobServiceName() {
        return System.getenv("USER_MANAGEMENT_APPLICATION_NAME");
    }

    @Override
    protected String getJobUUID() {
        return "12c56635-8298-4473-922d-c36c6c66e163";
    }

    @Override
    protected String getJobName() {
        return "Soft-deleted users removal";
    }

    @Override
    protected String getJobDescription() {
        return "This background job is responsible for removing soft-deleted users from the database. " +
            "Removal occurs after a given amount of time has passed since the user was soft-deleted.";
    }

    @Override
    protected AcctJobCron getCron() {
        return
            AcctJobCron.acctJobCronWithCronExpression(
                SpringCronExpressionBuilder.daily()
                    .atHour(19)
                    .atMinute(16)
                    .atSecond(0)
                    .build()
            );
    }

    @Override
    protected long getMaxDelayMs() {
        return 5000;
    }

    @Override
    @Transactional
    protected void internalJobLogicRunnable() {
        // Get the expired users
        final Collection<AcctUser> expiredUsers =
            userManagementService.findSoftDeletedUsersDeletedDaysAgo(softDeletedUsersExpiryPeriodInDays);

        // If there are no expired users then there is nothing to do
        if (expiredUsers.isEmpty()) {
            return;
        }

        // TODO: delete all associated objects from all the other services
        //       (requires the other services to be build first)

        // Finally, delete all the users
        userManagementService.permanentlyDeleteUsers(expiredUsers);
    }
}
