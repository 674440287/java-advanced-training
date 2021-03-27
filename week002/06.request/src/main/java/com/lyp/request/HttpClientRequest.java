package com.lyp.request;

import okhttp3.Response;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientRequest {
    public static void main(String[] args) throws IOException {
        doGet("http://localhost:8801");
    }

    private static String doGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);

        try (CloseableHttpClient build = HttpClientBuilder.create().build();
             CloseableHttpResponse execute = build.execute(httpGet)) {
            return EntityUtils.toString(execute.getEntity());
        }
    }

}
