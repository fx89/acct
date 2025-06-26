package com.desolatetimelines.acct.common.utils;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Provides utility methods that use the HTTP client
 */
public abstract class HttpClientUtils {

    /**
     * Creates a new instance of the {@link HttpClient} with a plugged-in external
     * {@link CookieManager} that can be used to set and unset cookies
     */
    public static HttpClientWithCookieManager createHttpClientWithCookieManager() {
        // Create a cookie handler for the HTTP client
        final CookieManager cookieManager = new CookieManager();

        // Create the HTTP client with the cookie handler
        final HttpClient httpClient = HttpClient.newBuilder().cookieHandler(cookieManager).build();

        // Package the two and return a reference
        return new HttpClientWithCookieManager(httpClient, cookieManager);
    }

    /**
     * Performs a POST request to the given URL, with the given form data,
     * using the referenced {@link HttpClientWithCookieManager} and returns
     * an {@link HttpResponseWithCookies}
     *
     * @param httpClientWithCookieManager the referenced HttpClientWithCookieManager
     * @param url                         the given URL
     * @param data                        the given form data
     */
    public static HttpResponseWithCookies<String> postFormUrlEncoded(
        HttpClientWithCookieManager httpClientWithCookieManager,
        String url,
        Map<String, String> data
    ) throws IOException, InterruptedException {
        // URL-encode the data
        String formData = urlEncodeMap(data);

        // Create the URI
        final URI uri = URI.create(url);

        // Create the request
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(formData))
            .build();

        // Run the request and return the response
        return runHttpRequest(httpClientWithCookieManager, request, uri);
    }

    /**
     * Performs a GET request to the given URL, with the given query parameters,
     * using the referenced {@link HttpClientWithCookieManager} and returns an
     * {@link HttpResponseWithCookies}
     *
     * @param httpClientWithCookieManager the referenced HttpClientWithCookieManager
     * @param url                         the given URL
     * @param queryParams                 the given query parameters
     */
    public static HttpResponseWithCookies<String> getRequest(
        HttpClientWithCookieManager httpClientWithCookieManager,
        String url,
        Map<String, String> queryParams
    ) throws IOException, InterruptedException {
        // URL-encode the parameters
        String urlEncodedQueryParams = urlEncodeMap(queryParams);

        // Create the URI
        final URI uri = URI.create(url + "?" + urlEncodedQueryParams);

        // Create the request
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

        // Run the request and return the response
        return runHttpRequest(httpClientWithCookieManager, request, uri);
    }

    private static String urlEncodeMap(Map<String, String> map) {
        return
            map.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), UTF_8))
                .collect(Collectors.joining("&"));
    }

    private static HttpResponseWithCookies<String> runHttpRequest(
        HttpClientWithCookieManager httpClientWithCookieManager,
        HttpRequest httpRequest,
        URI uri
    ) throws IOException, InterruptedException {
        // Run the HTTP query and get the response
        final HttpResponse<String> response =
            httpClientWithCookieManager.httpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Get the cookies
        final List<HttpCookie> cookies =
            httpClientWithCookieManager.cookieManager().getCookieStore().get(uri);

        // Process the cookies (if any) and put them in a simplified map that's ready to be used
        final Map<String, String> simplifiedCookies = new HashMap<>(cookies.size());
        cookies.forEach(cookie -> simplifiedCookies.put(cookie.getName(), cookie.getValue()));

        // Create and return the response
        return new HttpResponseWithCookies<>(response, simplifiedCookies);
    }

    /**
     * Container for an {@link HttpClient} and the attached {@link CookieManager}
     */
    public record HttpClientWithCookieManager(
        HttpClient httpClient,
        CookieManager cookieManager
    ) implements AutoCloseable {

        @Override
        public void close() {
            httpClient.close();
        }
    }

    /**
     * Groups an {@link HttpResponse} together with the received cookies
     *
     * @param httpResponse the HttpResponse
     * @param cookies      the received cookies
     * @param <T>
     */
    public record HttpResponseWithCookies<T>(
        HttpResponse<T> httpResponse,
        Map<String, String> cookies
    ) {

    }

}
