package com.example.emotiondiary.EmotionDetect;

public class APIKey {
    private final String apiEndpoint;
    private final String subscriptionKey;
    public APIKey() {
        apiEndpoint=  "apiEndpoint";
        subscriptionKey = "subscriptionKey";
    }

    public String getApiEndpoint(){
        return apiEndpoint;
    }
    public String getSubscriptionKey(){
        return subscriptionKey;
    }
}
