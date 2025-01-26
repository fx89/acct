package com.desolatetimelines.acct.catalog.ws.client;

import com.desolatetimelines.acct.catalog.ws.endpoint.IconsEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.IconCreateRequest;
import com.desolatetimelines.acct.catalog.ws.model.IconProperties;
import com.desolatetimelines.acct.catalog.ws.model.IconUUIDResponse;
import com.desolatetimelines.acct.catalog.ws.model.IconsCountResponse;
import com.desolatetimelines.acct.common.ws.model.AcctPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@FeignClient(
    contextId = "${CATALOG_APPLICATION_NAME}-icons",
    name = "${CATALOG_APPLICATION_NAME}/${CATALOG_SERVER_CONTEXT_PATH}/icons"
)
public interface RESTIconsEndpointClient extends IconsEndpoint {

    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    IconUUIDResponse createIcon(@RequestBody IconCreateRequest request);

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    Collection<String> getIconCategories();

    @Override
    @GetMapping(value = "/count", produces = APPLICATION_JSON_VALUE)
    IconsCountResponse getIconsCount(
        @RequestParam(name = "iconNamePattern", required = false) String iconNamePattern,
        @RequestParam(name = "iconCategoryName", required = false) String iconCategoryName
    );

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    AcctPage<IconProperties> getIcons(
        @RequestParam(name = "iconNamePattern", required = false) String iconNamePattern,
        @RequestParam(name = "iconCategoryName", required = false) String iconCategoryName,
        @RequestParam(name = "pageNumber") int pageNumber,
        @RequestParam(name = "pageSize") int pageSize
    );

    @Override
    @GetMapping(value = "/icon", produces = TEXT_PLAIN_VALUE)
    String getIconBytesBase64(@RequestParam(name = "iconUUID") String iconUUID);

}
