package com.DailyTasks.core.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Activate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component(service = AuthService.class, immediate = true)
public class AuthService {

    private static final String TOKEN_URL = "https://dev-ksrmb6bif8odr1yh.us.auth0.com/oauth/token";
    private static final String CLIENT_ID = "PE8u0rVxkMdXt1ncjArgmwdBpk8tidGQ";
    private static final String CLIENT_SECRET = "DDArv3xtAj6CtOTq18SCisTYWmwgA5E5eis74becGyDHUc8cwUGImRP86JtjRgWq";
    private static final String AUDIENCE = "https://dev-ksrmb6bif8odr1yh.us.auth0.com/api/v2/";

    private String accessToken;

    @Activate
    public void activate() {
        this.accessToken = fetchAccessToken();
    }

    public String getAccessToken() {
        if (accessToken == null || accessToken.isEmpty()) {
            accessToken = fetchAccessToken();
        }
        return accessToken;
    }

    private String fetchAccessToken() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(TOKEN_URL);
            post.setHeader("Content-Type", "application/json");

            // Using org.json.JSONObject
            JSONObject requestBody = new JSONObject();
            requestBody.put("client_id", CLIENT_ID);
            requestBody.put("client_secret", CLIENT_SECRET);
            requestBody.put("audience", AUDIENCE);
            requestBody.put("grant_type", "client_credentials");

            StringEntity entity = new StringEntity(requestBody.toString());
            post.setEntity(entity);

            CloseableHttpResponse response = httpClient.execute(post);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder responseString = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseString.append(line);
            }

            JSONObject jsonResponse = new JSONObject(responseString.toString());
            return jsonResponse.optString("access_token", null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
