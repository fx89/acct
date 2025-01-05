package com.desolatetimelines.acct.common.service;

import com.desolatetimelines.acct.common.model.ErrorCategory;
import com.desolatetimelines.acct.common.model.ErrorCode;
import com.desolatetimelines.acct.common.model.ErrorThrowingServiceDescription;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractErrorCodesRegistryServiceTest {

    @Test
    public void AbstractErrorCodesRegistryService_works() {
        // Create an error codes registry with a given set of error codes
        final AbstractErrorCodesRegistryService errorCodesRegistry =
            new AbstractErrorCodesRegistryService(10) {
                @Override
                protected void initializeServiceSpecificErrorCodes() {
                    resolveErrorCode("Cat 1", "Err 1", "Cat 1 Err 1");
                    resolveErrorCode("Cat 2", "Err 1", "Cat 2 Err 1");
                    resolveErrorCode("Cat 1", "Err 2", "Cat 1 Err 2");
                    resolveErrorCode("Cat 2", "Err 2", "Cat 2 Err 2");
                    resolveErrorCode("Cat 3", "Err 1", "Cat 3 Err 1");
                }
            };

        // Get the error codes from the registry
        final ErrorThrowingServiceDescription serviceDescription = errorCodesRegistry.findAll();

        // The service number should be set correctly
        assertEquals(10, serviceDescription.errorThrowingServiceNumber());

        // The categories should be set correctly
        assertEquals(6, serviceDescription.errorCategories().size());
        final List<String> categoryNames = serviceDescription.errorCategories().stream().map(ErrorCategory::errorCategoryName).toList();
        assertTrue(categoryNames.contains("Cat 1"));
        assertTrue(categoryNames.contains("Cat 2"));
        assertTrue(categoryNames.contains("Cat 3"));

        // Get the category names from the registry
        final Set<String> foundCategoryNames = errorCodesRegistry.findAllCategoryNames();

        // Make sure the category names are retrieved correctly
        assertEquals(6, foundCategoryNames.size());
        assertTrue(foundCategoryNames.contains("Cat 1"));
        assertTrue(foundCategoryNames.contains("Cat 2"));
        assertTrue(foundCategoryNames.contains("Cat 3"));

        // Check that the error codes in Cat 1 have been registered properly
        final List<ErrorCode> cat1ErrorCodes = errorCodesRegistry.findAllByCategoryName("Cat 1").orElse(emptyList());
        final List<String> cat1ErrorNames = cat1ErrorCodes.stream().map(ErrorCode::getErrorDescription).toList();
        assertTrue(cat1ErrorNames.contains("Cat 1 Err 1"));
        assertTrue(cat1ErrorNames.contains("Cat 1 Err 2"));

        // Check that the error codes in Cat 2 have been registered properly
        final List<ErrorCode> cat2ErrorCodes = errorCodesRegistry.findAllByCategoryName("Cat 2").orElse(emptyList());
        final List<String> cat2ErrorNames = cat2ErrorCodes.stream().map(ErrorCode::getErrorDescription).toList();
        assertTrue(cat2ErrorNames.contains("Cat 2 Err 1"));
        assertTrue(cat2ErrorNames.contains("Cat 2 Err 2"));

        // Check that the error codes in Cat 3 have been registered properly
        final List<ErrorCode> cat3ErrorCodes = errorCodesRegistry.findAllByCategoryName("Cat 3").orElse(emptyList());
        final List<String> cat3ErrorNames = cat3ErrorCodes.stream().map(ErrorCode::getErrorDescription).toList();
        assertTrue(cat3ErrorNames.contains("Cat 3 Err 1"));

        // Make sure the error codes are set correctly
        final ErrorCode cat1Err1 = cat1ErrorCodes.stream().filter(errCode -> "Err 1".equals(errCode.getErrorName())).findFirst().orElseThrow();
        assertEquals("0x0A00A0000", cat1Err1.getErrorCode());
        final ErrorCode cat1Err2 = cat1ErrorCodes.stream().filter(errCode -> "Err 2".equals(errCode.getErrorName())).findFirst().orElseThrow();
        assertEquals("0x0A00A0001", cat1Err2.getErrorCode());
        final ErrorCode cat2Err1 = cat2ErrorCodes.stream().filter(errCode -> "Err 1".equals(errCode.getErrorName())).findFirst().orElseThrow();
        assertEquals("0x0A00B0000", cat2Err1.getErrorCode());
        final ErrorCode cat2Err2 = cat2ErrorCodes.stream().filter(errCode -> "Err 2".equals(errCode.getErrorName())).findFirst().orElseThrow();
        assertEquals("0x0A00B0001", cat2Err2.getErrorCode());
        final ErrorCode cat3Err1 = cat3ErrorCodes.stream().filter(errCode -> "Err 1".equals(errCode.getErrorName())).findFirst().orElseThrow();
        assertEquals("0x0A00C0000", cat3Err1.getErrorCode());
    }

}
