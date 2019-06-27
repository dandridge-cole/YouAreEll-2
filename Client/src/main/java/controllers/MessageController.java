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

    public ArrayList<Message> getMessages() {
        String path = "/messages";
        HttpResponse<JsonNode> response = TransactionController.get(path);
        return objectFromJSON(response.getBody().toString());
    }
    public ArrayList<Message> getMessagesForId(Id Id) {
        String path = "/ids/"+Id+"/messages";
        HttpResponse<JsonNode> response = TransactionController.get(path);
        return objectFromJSON(response.getBody().toString());
    }
    public Message getMessageForSequence(String seq) {
        ArrayList<Message> messages = getMessages();
        for(Message message:messages)if(message.getSequence().equals(seq))return message;
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        List<Message> messages = this.getMessagesForId(myId);
        ArrayList<Message> selected = new ArrayList<>();
        for(Message message:messages)if(message.getFromId().equals(friendId)) selected.add(message);
        return selected;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        String path = "/ids/"+myId+"/messages";
        Message message = new Message(msg.getMessage(),myId.getGithubID(),toId.getGithubID());
        String response = TransactionController.post(path,objectToJSON(message));
        List<Message> responseList = objectFromJSON(response);
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