package controllers;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import models.Message;

public class TransactionController {
    static private final String rootURL = "http://zipcode.rocks:8085";
    static private String fullURL;

    public TransactionController() {
    }

    public TransactionController(String path) {
        fullURL=rootURL+path;
    }

    static HttpResponse<JsonNode> get(String path){
        fullURL=rootURL+path;
        return Unirest.get(fullURL).asJson();
    }

    static String post (String path, String json){
        fullURL=rootURL+path;
        return Unirest.post(fullURL).body(json).asString().toString();
    }
}
