package com.desolatetimelines.acct.authorization.controller;

import com.desolatetimelines.acct.authorization.model.UserAccessTokenResponse;
import com.desolatetimelines.acct.authorization.model.CredentialsRequest;
import com.desolatetimelines.acct.authorization.service.CustomLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.desolatetimelines.acct.authorization.config.SecurityConfig.CUSTOM_LOGIN_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(CUSTOM_LOGIN_PATH)
public class CustomLoginController {

    private final CustomLoginService customLoginService;

    public CustomLoginController(CustomLoginService customLoginService) {
        this.customLoginService = customLoginService;
    }

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public UserAccessTokenResponse login(@RequestBody CredentialsRequest request) {
        return
            new UserAccessTokenResponse(
                customLoginService.loginAndAuthorize(
                    request.clientId(),
                    request.clientSecret(),
                    request.username(),
                    request.password()
                )
            );
    }

}
