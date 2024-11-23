package com.desolatetimelines.acct.common.rest.service;

import com.desolatetimelines.acct.common.rest.exception.EurekaHostResolverBadHostDefinitionException;
import com.desolatetimelines.acct.common.rest.exception.EurekaHostResolverNotFoundException;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static java.util.Collections.emptyList;

/**
 * Retrieves host information for application instances registered with a given Eureka service.
 * The Eureka service is identified by the following environment variables: <ul>
 * <li><b>EUREKA_SERVER_SCHEME</b> - http or https</li>
 * <li><b>EUREKA_SERVER_HOST</b> - the host name or IP address of the Eureka service</li>
 * <li><b>EUREKA_SERVER_PORT</b> - The port on which the Eureka service is listening</li>
 * </ul>
 * When resolving the host, some preferences may be set via {@link EurekaHostResolverParameters parameters}.<br />
 * <br />
 * The following exceptions are thrown: <ul>
 * <li><b>{@link IllegalArgumentException}</b> - Bad application name or resolver parameters</li>
 * <li><b>{@link EurekaHostResolverNotFoundException}</b> - Application not found or no running instances present</li>
 * <li><b>{@link EurekaHostResolverBadHostDefinitionException}</b> - The requested host attributes are not available</li>
 * </ul>
 */
@Service
public class EurekaHostResolver {

    private final EurekaClient eurekaClient;

    private final String eurekaServiceUrl;

    private final Random randomRunningInstanceIndexesGenerator = new Random();

    /**
     * The default {@link EurekaHostResolverParameters host resolver parameters} specify that: <ul>
     * <li>{@link EurekaHostResolverHostChoosingAlgorithm choosing algorithm} = {@link EurekaHostResolverHostChoosingAlgorithm#RANDOM RANDOM}</li>
     * <li>{@link EurekaHostResolverReturnPreference return preference} = {@link EurekaHostResolverReturnPreference#PREFER_HOSTNAME PREFER_HOSTNAME}</li>
     * </ul>
     */
    public static final EurekaHostResolverParameters DEFAULT_PARAMETERS =
        parametersBuilder()
            .withChoosingAlgorithm(EurekaHostResolverHostChoosingAlgorithm.RANDOM)
            .withReturnPreference(EurekaHostResolverReturnPreference.PREFER_HOSTNAME)
            .build();

    public EurekaHostResolver(
        EurekaClient eurekaClient,
        @Value("${EUREKA_SERVER_SCHEME}") String eurekaServerScheme,
        @Value("${EUREKA_SERVER_HOST}") String eurekaServerHost,
        @Value("${EUREKA_SERVER_PORT}") String eurekaServerPort
    ) {
        this.eurekaClient = eurekaClient;
        this.eurekaServiceUrl = eurekaServerScheme + "://" + eurekaServerHost + ":" + eurekaServerPort;
    }

    /**
     * Resolves the host of one of the instances registered with the Eureka service for the application
     * with the given application name using the default {@link EurekaHostResolverParameters parameters}
     *
     * @param applicationName the given application name
     */
    public String resolveHostAddressByApplicationName(String applicationName) {
        return resolveHostAddressByApplicationName(applicationName, DEFAULT_PARAMETERS);
    }

    /**
     * Resolves the host of one of the instances registered with the Eureka service for the application
     * with the given application name using the given {@link EurekaHostResolverParameters parameters}<br />
     * <br />
     * See also: {@link EurekaHostResolver#parametersBuilder}
     *
     * @param applicationName the given application name
     * @param parameters      the given parameters
     */
    public String resolveHostAddressByApplicationName(
        String applicationName,
        EurekaHostResolverParameters parameters
    ) {
        // Make sure the application name is not missing or blank
        if (applicationName == null || applicationName.isBlank()) {
            throw new IllegalArgumentException("Blank or missing application name");
        }

        // Make sure the parameters are correct
        if (parameters == null || parameters.choosingAlgorithm() == null || parameters.returnPreference() == null) {
            throw new IllegalArgumentException("Invalid EurekaHostResolverParameters");
        }

        // Get the instances of the application
        final List<InstanceInfo> instances = getInstances(applicationName);

        // If there are no instances found, throw an exception
        if (instances.isEmpty()) {
            throw new EurekaHostResolverNotFoundException(
                "No instances found for application [" + applicationName + "]"
            );
        }

        // Get the instances that are up
        final List<InstanceInfo> runningInstances =
            instances.stream()
                .filter(i -> Objects.equals("UP", i.getStatus().name()))
                .toList();

        // If there are no running instances, throw an exception
        if (runningInstances.isEmpty()) {
            throw new EurekaHostResolverNotFoundException(
                "No running instances found for application [" + applicationName + "]"
            );
        }

        // Pick a running instance according to the preferred choosing algorithm (first / random)
        final int chosenRunningInstanceIndex =
            parameters.choosingAlgorithm == EurekaHostResolverHostChoosingAlgorithm.FIRST
                ? 0
                : randomRunningInstanceIndexesGenerator.nextInt(runningInstances.size());

        // Get the instance
        final InstanceInfo chosenRunningInstance = runningInstances.get(chosenRunningInstanceIndex);

        // Get the instance's host name or IP address, according to the return preference
        final String chosenRunningInstanceHost =
            switch (parameters.returnPreference) {
                case HOSTNAME -> Optional
                    .ofNullable(chosenRunningInstance.getHostName())
                    .orElseThrow(() -> new EurekaHostResolverBadHostDefinitionException(
                        "No host name specified for the instance"
                    ));
                case IP_ADDRESS -> Optional
                    .ofNullable(chosenRunningInstance.getIPAddr())
                    .orElseThrow(() -> new EurekaHostResolverBadHostDefinitionException(
                        "No IP address specified for the instance"
                    ));
                case PREFER_HOSTNAME -> Optional
                    .ofNullable(chosenRunningInstance.getHostName())
                    .orElseGet(() -> Optional
                        .ofNullable(chosenRunningInstance.getIPAddr())
                        .orElseThrow(() -> new EurekaHostResolverBadHostDefinitionException(
                            "No IP address or host name specified for the instance"
                        ))
                    );
                case PREFER_IP_ADDRESS -> Optional
                    .ofNullable(chosenRunningInstance.getIPAddr())
                    .orElseGet(() -> Optional
                        .ofNullable(chosenRunningInstance.getHostName())
                        .orElseThrow(() -> new EurekaHostResolverBadHostDefinitionException(
                            "No IP address or host name specified for the instance"
                        ))
                    );
            };

        // Get the instance's port
        int chosenRunningInstancePort =
            chosenRunningInstance.isPortEnabled(InstanceInfo.PortType.SECURE)
                ? chosenRunningInstance.getSecurePort()
                : chosenRunningInstance.getPort();

        // Throw an exception if the port is not specified
        if (chosenRunningInstancePort == 0) {
            throw new EurekaHostResolverBadHostDefinitionException("No port specified for the instance");
        }

        // Compute the protocol
        final String chosenRunningInstanceProtocol =
            chosenRunningInstance.isPortEnabled(InstanceInfo.PortType.SECURE)
                ? "https"
                : "http";

        // Return the instance host

        return chosenRunningInstanceProtocol + "://" + chosenRunningInstanceHost + ":" + chosenRunningInstancePort;
    }

    private List<InstanceInfo> getInstances(String applicationName) {
        // Get the applications registered with the Eureka service
        final Applications applications = eurekaClient.getApplications(eurekaServiceUrl);

        // If nothing found, return an empty list
        if (applications == null) {
            return emptyList();
        }

        // Out of all the registered applications, get the one wirth the required name
        final Application registeredApplication = applications.getRegisteredApplications(applicationName);

        // If nothing found, return an empty list
        if (registeredApplication == null) {
            return emptyList();
        }

        // If found, return the instances of the application or, if there are no instances, return an empty list
        return Optional.ofNullable(registeredApplication.getInstances()).orElse(emptyList());
    }

    /**
     * Allows specifying how the {@link EurekaHostResolver host resolver} chooses an application instance: <ul>
     * <li><b>FIRST</b> - the first instance returned by the Eureka service is chosen</li>
     * <li><b>RANDOM</b> - a random instance is chosen</li>
     * </ul>
     */
    public enum EurekaHostResolverHostChoosingAlgorithm {
        /**
         * Choose the first instance returned by the Eureka service
         */
        FIRST,

        /**
         * Choose a random instance out of those returned by the Eureka service
         */
        RANDOM
    }

    /**
     * Allows specifying what host identifier to return: <ul>
     * <li><b>HOSTNAME</b> - Return the host name. If not available, throw an exception.</li>
     * <li><b>IP_ADDRESS</b> - Return the IP address. If not available, throw an exception. </li>
     * <li><b>PREFER_HOSTNAME</b> - Return the host name. If not available, return the IP address. If not available, throw an exception.</li>
     * <li><b>PREFER_IP_ADDRESS</b> - Return the IP address. If not available, return the host name. If not available, throw an exception.</li>
     * </ul>
     */
    public enum EurekaHostResolverReturnPreference {
        /**
         * Return the host name or throw an exception
         */
        HOSTNAME,

        /**
         * Return the IP address or throw an exception
         */
        IP_ADDRESS,

        /**
         * Return the host name and, if the host name is not available, return the IP address or throw an exception
         */
        PREFER_HOSTNAME,

        /**
         * Return the IP address and, if the host name is not available, return the host name or throw an exception
         */
        PREFER_IP_ADDRESS
    }

    /**
     * Allows specifying preferences for the functionality of the {@link EurekaHostResolver}
     *
     * @param choosingAlgorithm Allows specifying how application instances are chosen: <ul>
     *                          <li><b>FIRST</b> - the first instance returned by the Eureka service is chosen</li>
     *                          <li><b>RANDOM</b> - a random instance is chosen</li>
     *                          </ul>
     * @param returnPreference  Allows specifying which identifier of the chosen instance is returned: <ul>
     *                          <li><b>HOSTNAME</b> - Return the host name. If not available, throw an exception.</li>
     *                          <li><b>IP_ADDRESS</b> - Return the IP address. If not available, throw an exception. </li>
     *                          <li><b>PREFER_HOSTNAME</b> - Return the host name. If not available, return the IP address. If not available, throw an exception.</li>
     *                          <li><b>PREFER_IP_ADDRESS</b> - Return the IP address. If not available, return the host name. If not available, throw an exception.</li>
     *                          </ul>
     */
    public record EurekaHostResolverParameters(
        EurekaHostResolverHostChoosingAlgorithm choosingAlgorithm,
        EurekaHostResolverReturnPreference returnPreference
    ) {
        public static EurekaHostResolverParametersBuilder builder() {
            return new EurekaHostResolverParametersBuilder();
        }

        public static class EurekaHostResolverParametersBuilder {
            private EurekaHostResolverHostChoosingAlgorithm choosingAlgorithm =
                EurekaHostResolverHostChoosingAlgorithm.RANDOM;

            private EurekaHostResolverReturnPreference returnPreference =
                EurekaHostResolverReturnPreference.PREFER_HOSTNAME;

            protected EurekaHostResolverParametersBuilder() {

            }

            /**
             * Sets the {@link EurekaHostResolverHostChoosingAlgorithm choosing algorithm}
             */
            public EurekaHostResolverParametersBuilder withChoosingAlgorithm(
                EurekaHostResolverHostChoosingAlgorithm choosingAlgorithm
            ) {
                this.choosingAlgorithm = choosingAlgorithm;
                return this;
            }

            /**
             * Sets the {@link EurekaHostResolverReturnPreference return preference}
             */
            public EurekaHostResolverParametersBuilder withReturnPreference(
                EurekaHostResolverReturnPreference returnPreference
            ) {
                this.returnPreference = returnPreference;
                return this;
            }

            public EurekaHostResolverParameters build() {
                return new EurekaHostResolverParameters(choosingAlgorithm, returnPreference);
            }
        }
    }

    /**
     * Creates a builder for the {@link EurekaHostResolverParameters parameters} that go to the
     * {@link EurekaHostResolver#resolveHostAddressByApplicationName(String, EurekaHostResolverParameters) resolveHostByApplicationName}
     * method
     */
    public static EurekaHostResolverParameters.EurekaHostResolverParametersBuilder parametersBuilder() {
        return EurekaHostResolverParameters.builder();
    }

}
