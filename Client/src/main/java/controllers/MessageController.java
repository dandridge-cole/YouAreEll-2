package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
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
        return messagesFromJSON(response.getBody().toString());
    }
    public List<Message> getMessagesForId(Id Id) {
        String path = "/ids/"+Id+"/messages";
        HttpResponse<JsonNode> response = TransactionController.get(path);
        return messagesFromJSON(response.getBody().toString());
    }
    public Message getMessageForSequence(String seq) {
        String path = "/messages/"+seq;
        HttpResponse<JsonNode> response = TransactionController.get(path);
        List<Message> responseList = messagesFromJSON(response.getBody().toString());
        return responseList.get(0);
    }

    public List<Message> getMessagesFromFriend(Id myId, Id friendId) {
        String path = "/ids/"+myId+"/from/"+friendId;
        HttpResponse<JsonNode> response = TransactionController.get(path);
        return messagesFromJSON(response.getBody().toString());
    }

//    public Message postMessage(Id myId, Id toId, Message msg) {
//        String path = "/ids/"+myId+"/messages";
//        Message message = new Message(msg.getMessage(),myId.getGithubID(),toId.getGithubID());
//        JsonNode response = TransactionController.post(path,objectToJSON(message));
//        List<Message> responseList = messagesFromJSON(response.toString());
//        return responseList.get(0);
//    }

    List<Message> messagesFromJSON(String json){
        try {

            List<Message> names = om.readValue(
                    json,
//                    "[{\n" +
//                    "        \"sequence\": \"c5fbeb4f852c3991fbd1b1e244370074aea2a0b9\",\n" +
//                    "        \"timestamp\": \"2019-06-26T20:25:58.11714073Z\",\n" +
//                    "        \"fromid\": \"calebpowell-oak\",\n" +
//                    "        \"toid\": \"spooky\",\n" +
//                    "        \"message\": \"You is a ghost\"\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "        \"sequence\": \"3790814f9d1c43848e9de41ec3d8a3ef142a37a6\",\n" +
//                    "        \"timestamp\": \"2019-06-26T20:24:22.930458311Z\",\n" +
//                    "        \"fromid\": \"calebpowell-oak\",\n" +
//                    "        \"toid\": \"\",\n" +
//                    "        \"message\": \"Yew is a tree\"\n" +
//                    "    }]",

                    new TypeReference<List<Message>>() { });

          //  List<Message> objects = Arrays.asList(om.readValue(json.toString(), Message[].class));
            return names;
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
            return null;
        }
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

    }*/
}