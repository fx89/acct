package com.desolatetimelines.acct.authorization.service;

import com.desolatetimelines.acct.authorization.data.service.AcctAuthorizationServerDataService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class AcctUserDetailsService implements UserDetailsService {

    private final AcctAuthorizationServerDataService dataService;

    public AcctUserDetailsService(AcctAuthorizationServerDataService dataService) {
        this.dataService = dataService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return
            dataService.getUserByLoginName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}
