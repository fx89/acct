package com.desolatetimelines.acct.common.ws.controller;

import com.desolatetimelines.acct.common.model.ErrorThrowingServiceDescription;
import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/errors")
@SuppressWarnings("unused")
public class ErrorCodesController {

    private final AbstractErrorCodesRegistryService errorCodesRegistry;

    public ErrorCodesController(AbstractErrorCodesRegistryService errorCodesRegistry) {
        this.errorCodesRegistry = errorCodesRegistry;
    }

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ErrorThrowingServiceDescription findAll() {
        return errorCodesRegistry.findAll();
    }

}
