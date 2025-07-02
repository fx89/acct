package com.desolatetimelines.acct.security.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

@Service
public class AcctSecurityErrorCodesRegistryService extends AbstractErrorCodesRegistryService {

    protected AcctSecurityErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("SECURITY_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {
        
    }

}
