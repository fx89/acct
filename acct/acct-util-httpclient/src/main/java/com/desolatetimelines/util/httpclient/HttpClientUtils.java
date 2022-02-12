package com.desolatetimelines.util.httpclient;

import com.desolatetimelines.util.httpclient.exception.EmptyHttpResponseException;
import com.desolatetimelines.util.httpclient.exception.HttpClientUtilsException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class HttpClientUtils {

    public static String getSSLPageContentAsString(String uri) throws HttpClientUtilsException {
        SSLConnectionSocketFactory csf = makeSSLConnectionSocketFactory();

        try (CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build()) {
            HttpGet request = new HttpGet(uri);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();

                if (entity == null) {
                    throw new EmptyHttpResponseException("Received empty response from [" + uri + "]");
                }

                return EntityUtils.toString(entity);
            }
        } catch (IOException exc) {
            throw new HttpClientUtilsException(
                    "Error executing GET request [" + uri + "]: " + exc.getMessage(), exc
                );
        }
    }

    private static SSLConnectionSocketFactory makeSSLConnectionSocketFactory() throws HttpClientUtilsException {
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustAllStrategy());
            return new SSLConnectionSocketFactory(builder.build());
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException exc) {
            throw new HttpClientUtilsException(
                    "Error building SSLConnectionSocketFactory: " + exc.getMessage(),
                    exc
                );
        }
    }

}
