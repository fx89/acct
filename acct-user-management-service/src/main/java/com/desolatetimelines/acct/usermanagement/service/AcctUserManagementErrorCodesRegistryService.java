package com.desolatetimelines.acct.usermanagement.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

@Service
public class AcctUserManagementErrorCodesRegistryService extends AbstractErrorCodesRegistryService {

    protected AcctUserManagementErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("USER_MANAGEMENT_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {

    }

}
