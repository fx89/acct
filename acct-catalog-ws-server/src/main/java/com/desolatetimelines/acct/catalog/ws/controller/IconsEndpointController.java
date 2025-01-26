package com.desolatetimelines.acct.catalog.ws.controller;

import com.desolatetimelines.acct.catalog.model.AcctIconCategory;
import com.desolatetimelines.acct.catalog.service.AcctCatalogService;
import com.desolatetimelines.acct.catalog.ws.endpoint.IconsEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.IconCreateRequest;
import com.desolatetimelines.acct.catalog.ws.model.IconUUIDResponse;
import com.desolatetimelines.acct.catalog.ws.model.IconsCountResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.catalog.privilegesprovider.model.CatalogPrivilegeIds.*;
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

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ICONS_GET_CATEGORIES + "')")
    @GetMapping(value = "/iconCategories", produces = APPLICATION_JSON_VALUE)
    public Collection<String> getIconCategories() {
        return
            catalogService.getIconCategories()
                .stream()
                .map(AcctIconCategory::getIconCategoryName)
                .collect(Collectors.toSet());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ICONS_READ + "')")
    @GetMapping(value = "/count", produces = APPLICATION_JSON_VALUE)
    public IconsCountResponse getIconsCount(
        @RequestParam(name = "iconNamePattern", required = false) String iconNamePattern,
        @RequestParam(name = "iconCategoryName", required = false) String iconCategoryName
    ) {
        return
            new IconsCountResponse(
                catalogService.countIcons(iconNamePattern, iconCategoryName)
            );
    }

}
