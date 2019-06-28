package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import models.Id;
import models.Message;

public class MessageController extends DataController<Message> {
    private ObjectMapper om = new ObjectMapper();
    private HashSet<Message> messagesSeen;
    // why a HashSet??

    public List<Message> getMessages() {
        String path = "/messages";
        HttpResponse<JsonNode> response = TransactionController.get(path);
        return messagesFromJSON(response.getBody());
    }
    public List<Message> getMessagesForId(Id Id) {
        String path = "/ids/"+Id+"/messages";
        HttpResponse<JsonNode> response = TransactionController.get(path);
        return messagesFromJSON(response.getBody());
    }
    public Message getMessageForSequence(String seq) {
        String path = "/messages/"+seq;
        HttpResponse<JsonNode> response = TransactionController.get(path);
        List<Message> responseList = messagesFromJSON(response.getBody());
        return responseList.get(0);
    }

    public List<Message> getMessagesFromFriend(Id myId, Id friendId) {
        String path = "/ids/"+myId+"/from/"+friendId;
        HttpResponse<JsonNode> response = TransactionController.get(path);
        return messagesFromJSON(response.getBody());
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        String path = "/ids/"+myId+"/messages";
        Message message = new Message(msg.getMessage(),myId.getGithubID(),toId.getGithubID());
        JsonNode response = TransactionController.post(path,objectToJSON(message));
        List<Message> responseList = messagesFromJSON(response);
        return responseList.get(0);
    }
/*
    private ArrayList<Message> messagesFromJSON(String json){
        try {
            ArrayList<Message> messages = om.readValue(json, new TypeReference<List<Message>>() {});
            return messages;
        } catch (IOException ioe){
            System.out.println(ioe.getStackTrace());
            return null;
        }
    }
    private String messageToJSON(Message message){
        try{
            String json = om.writeValueAsString(message);
            return json;
        } catch (JsonProcessingException jpe){
            System.out.println(jpe.getStackTrace());
            return null;
        }

    }*/
}