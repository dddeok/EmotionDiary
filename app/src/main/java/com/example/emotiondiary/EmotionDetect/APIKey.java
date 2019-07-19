package com.example.emotiondiary.EmotionDetect;

public class APIKey {
    private final String apiEndpoint;
    private final String subscriptionKey;
    public APIKey() {
        apiEndpoint=  "https://faceapifordddeok.cognitiveservices.azure.com/face/v1.0";
        subscriptionKey = "4c7f83a1899e4b70b5e296078b520b3e";
    }

    public String getApiEndpoint(){
        return apiEndpoint;
    }
    public String getSubscriptionKey(){
        return subscriptionKey;
    }
}
