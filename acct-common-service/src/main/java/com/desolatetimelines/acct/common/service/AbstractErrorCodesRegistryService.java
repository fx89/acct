package com.desolatetimelines.acct.common.service;

import com.desolatetimelines.acct.common.model.ErrorCategory;
import com.desolatetimelines.acct.common.model.ErrorCode;
import com.desolatetimelines.acct.common.model.ErrorThrowingServiceDescription;
import com.desolatetimelines.acct.common.utils.SynchronizedOverflowingIntSequence;

import java.util.*;

import static com.desolatetimelines.acct.common.util.ErrorCodeUtils.computeErrorCode;
import static java.util.Objects.requireNonNull;

/**
 * Base class for services that create and stores error codes for all the required situations
 */
public abstract class AbstractErrorCodesRegistryService {
    public String GENERIC_UNKNOWN;

    public String VALIDATION_BAD_PARAM;

    public String MISSING_CREDENTIALS;
    public String MISSING_GRANTS;

    /**
     * Unique identifier for the service across the ecosystem
     */
    private final int serviceNumber;

    /**
     * The actual registry - error categories mapped by their names for easy access
     */
    private final Map<String, ErrorCategory> registry = new HashMap<>();

    /**
     * Sequence for generating error category numbers (throws exception if overflowing)
     */
    private final SynchronizedOverflowingIntSequence errorCategoryNumbersSequence =
        new SynchronizedOverflowingIntSequence(0, 256, 1, false);

    /**
     * Sequences for generating error code numbers (throws exception if overflowing)
     */
    private final Map<String, SynchronizedOverflowingIntSequence> errorCodeNumbersSequencesByErrorCategoryName =
        new HashMap<>();

    protected AbstractErrorCodesRegistryService(int serviceNumber) {
        this.serviceNumber = serviceNumber;
        initializeReservedErrorCodes();
        initializeServiceSpecificErrorCodes();
    }

    private void initializeReservedErrorCodes() {
        // Declare reserved category names
        final String CAT_NAME_GENERIC = "Generic";
        final String CAT_NAME_REQUEST_VALIDATION = "Request validation";
        final String CAT_NAME_SECURITY = "Generic security exceptions";

        // Register categories for the declared category names
        resolveErrorCategory(CAT_NAME_GENERIC);
        resolveErrorCategory(CAT_NAME_REQUEST_VALIDATION);
        resolveErrorCategory(CAT_NAME_SECURITY);

        // Increase the category number up to 10 to reserve slots for future error categories
        errorCategoryNumbersSequence.incrementAndGet();
        errorCategoryNumbersSequence.incrementAndGet();
        errorCategoryNumbersSequence.incrementAndGet();
        errorCategoryNumbersSequence.incrementAndGet();
        errorCategoryNumbersSequence.incrementAndGet();
        errorCategoryNumbersSequence.incrementAndGet();
        errorCategoryNumbersSequence.incrementAndGet();

        // Register error codes for the reserved categories ...

        GENERIC_UNKNOWN = resolveErrorCode(
            CAT_NAME_GENERIC,
            "Unknown",
            "A generic error that represents any exceptional situation" +
                "that's not specifically mapped to any other error"
        );

        VALIDATION_BAD_PARAM = resolveErrorCode(
            CAT_NAME_REQUEST_VALIDATION,
            "Bad parameter",
            "A validation error that occurs when one or more parameters are set incorrectly"
        );

        MISSING_CREDENTIALS = resolveErrorCode(
            CAT_NAME_SECURITY,
            "Missing credentials",
            "The provided access token is either missing some required claims or is itself missing"
        );

        MISSING_GRANTS = resolveErrorCode(
            CAT_NAME_SECURITY,
            "Missing grants",
            "The user has successfully logged in but is missing the grants required for the operation"
        );
    }

    /**
     * Initializes the error codes by means of calling
     * {@link AbstractErrorCodesRegistryService#resolveErrorCode(String, String, String)}
     */
    protected abstract void initializeServiceSpecificErrorCodes();

    /**
     * Returns the error code for the error referenced by the given error category name and error name.
     * If such an error code is not registered, it is registered at this time along with the given error
     * description.
     *
     * @param errorCategoryName the given error category name
     * @param errorName         the given error name
     * @param errorDescription  the given error description
     * @return a HEX error code that uniquely identifies the error within the registry
     */
    protected String resolveErrorCode(String errorCategoryName, String errorName, String errorDescription) {
        // Make sure the mandatory parameters are provided
        requireNonNull(errorCategoryName, "Error category name not provided");
        requireNonNull(errorName, "Error name not provided");

        // Acquire the error category number
        final int errorCategoryNumber = resolveErrorCategory(errorCategoryName);

        // Acquire the error (register it if it does not exist)
        final ErrorCode error = resolveError(errorCategoryName, errorName);

        // Compute the error code
        final String errorCode = computeErrorCode(serviceNumber, errorCategoryNumber, error.getErrorNumber());

        // Update the error's attributes
        error.setErrorCode(errorCode);
        error.setErrorDescription(errorDescription);

        // Return the error code
        return errorCode;
    }

    /**
     * Returns an object that contains the ACCT service number and the list of
     * error categories along with all the errors registered under each category
     */
    public ErrorThrowingServiceDescription findAll() {
        return new ErrorThrowingServiceDescription(serviceNumber, registry.values());
    }

    /**
     * Returns a set containing the names of all the registered error categories
     */
    public Set<String> findAllCategoryNames() {
        return registry.keySet();
    }

    /**
     * Returns a list of error codes registered under the category with the given
     * category name or an empty optional if the category does not exist
     *
     * @param categoryName the given category name
     */
    public Optional<List<ErrorCode>> findAllByCategoryName(String categoryName) {
        return Optional.ofNullable(registry.get(categoryName)).map(ErrorCategory::errorCodes);
    }

    /**
     * Gets the number under which the referenced error category is registered.
     * If the referenced error category is not registered then it is registered
     * at this time.
     *
     * @param errorCategoryName the name of the referenced error category
     * @return the error category number
     */
    private int resolveErrorCategory(String errorCategoryName) {
        return
            registry.computeIfAbsent(
                errorCategoryName,
                errCatName -> ErrorCategory.builder()
                    .withErrorCategoryNumber(errorCategoryNumbersSequence.incrementAndGet())
                    .withErrorCategoryName(errorCategoryName)
                    .withErrorCodes(new ArrayList<>())
                    .build()
            ).errorCategoryNumber();
    }

    /**
     * Gets the error code with the given error name from the list of errors
     * for the referenced category. If the error is not registered then it is
     * registered at this time.
     *
     * @param errorCategoryName the name of the referenced category
     * @param errorName         the given error name
     */
    private ErrorCode resolveError(String errorCategoryName, String errorName) {
        // Get the list of errors for the category
        final List<ErrorCode> errorsList = registry.get(errorCategoryName).errorCodes();

        // Find the error code
        final Optional<ErrorCode> optionalErrorCode =
            errorsList.stream()
                .filter(errCode -> Objects.equals(errorName, errCode.getErrorName()))
                .findFirst();

        // If found, return the error number
        if (optionalErrorCode.isPresent()) {
            return optionalErrorCode.get();
        }

        // If not found then the error code must be created

        // First, resolve the error numbers sequence
        final SynchronizedOverflowingIntSequence errorNumbersSequence =
            errorCodeNumbersSequencesByErrorCategoryName.computeIfAbsent(
                errorCategoryName,
                errCatName -> new SynchronizedOverflowingIntSequence(
                    0,
                    65535,
                    1,
                    false
                )
            );

        // Then create the error code
        final ErrorCode errorCode =
            ErrorCode.builder()
                .withErrorNumber(errorNumbersSequence.incrementAndGet())
                .withErrorName(errorName)
                .build();

        // Add the error code to the category
        errorsList.add(errorCode);

        // Finally, return a reference to the error code
        return errorCode;
    }

}
