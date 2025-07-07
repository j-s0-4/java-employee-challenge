package com.reliaquest.api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    @Primary
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate(getReqFactory());
    }

    private ClientHttpRequestFactory getReqFactory() {
        final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(1000);
        clientHttpRequestFactory.setConnectionRequestTimeout(1000);
        final SocketConfig sc = SocketConfig.custom().setSoTimeout(Timeout.ofMilliseconds(5000)).build();
        final HttpClientConnectionManager hm = PoolingHttpClientConnectionManagerBuilder.create().setDefaultSocketConfig(sc).build();
        final HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(hm).build();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }
}
