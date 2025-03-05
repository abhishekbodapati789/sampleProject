package com.DailyTasks.core.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TextToSpeechModel {

    private static final String API_URL = "https://ai-powered-text-to-speech1.p.rapidapi.com/synthesize-speech";
    private static final String API_KEY = "6ed4fefb5dmshc98273e043566c5p1997c1jsnc5882df2f46e"; // Replace with a valid API Key
    private static final String API_HOST = "ai-powered-text-to-speech1.p.rapidapi.com";
    private static final String VOICE = "en-US-1";  

    @ValueMapValue
    private String text;

    private String audioUrl;

    @PostConstruct
    protected void init() {
        text = StringUtils.defaultIfBlank(text, "No text provided.");
        this.audioUrl = generateSpeech(text);
    }

    private String generateSpeech(String text) {
        try {
            // Prepare JSON request body
            String jsonInputString = String.format(
                "{\"voice_code\": \"%s\", \"text\": \"%s\", \"speed\": 1.0, \"pitch\": 1.0, \"output_type\": \"audio_url\"}",
                VOICE, text
            );

            // Setup HTTP connection
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-RapidAPI-Key", API_KEY);
            connection.setRequestProperty("X-RapidAPI-Host", API_HOST);
            connection.setDoOutput(true);

            // Send request data
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode != 200) {
                System.err.println("Error: HTTP " + responseCode);
                return "";
            }

            // Parse JSON response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                System.out.println("Response JSON: " + response);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode responseJson = mapper.readTree(response.toString());

                // Extract correct field for audio URL
                if (responseJson.has("data") && responseJson.get("data").has("url")) {
                    return responseJson.get("data").get("url").asText();
                } else {
                    System.err.println("Invalid response format: " + response.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getText() {
        return text;
    }

    public String getAudioUrl() {
        return audioUrl;
    }
}
