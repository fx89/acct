package com.desolatetimelines.acct.catalog.ws.controller;

import com.desolatetimelines.acct.catalog.service.AcctCatalogService;
import com.desolatetimelines.acct.catalog.ws.endpoint.IconsEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.IconCreateRequest;
import com.desolatetimelines.acct.catalog.ws.model.IconUUIDResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.desolatetimelines.acct.catalog.privilegesprovider.model.CatalogPrivilegeIds.ICONS_SAVE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/icons")
public class IconsEndpointController implements IconsEndpoint {

    private final AcctCatalogService catalogService;

    public IconsEndpointController(AcctCatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ICONS_SAVE + "')")
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public IconUUIDResponse createIcon(@RequestBody IconCreateRequest request) {
        return
            new IconUUIDResponse(
                catalogService.createIcon(
                    request.iconName(),
                    request.iconCategoryName(),
                    request.iconBase64()
                ).getIconUUID()
            );
    }

}
