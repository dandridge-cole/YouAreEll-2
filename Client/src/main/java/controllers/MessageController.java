package controllers;

import java.io.IOException;
import java.util.ArrayList;
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

    public List<Message> getMessages(String path) {
       // String path = "/messages";
        HttpResponse<JsonNode> response = TransactionController.get(path);
        if(response.getBody()==null) {
            List<Message> emptyList = new ArrayList<>();
            emptyList.add(new Message("","",""));
            return emptyList;
        }
        return messagesFromJSON(response.getBody().toString());
    }
    public List<Message> getMessages(String path, Id Id) {
        path = "/ids/"+Id+path;
        return getMessages(path);
    }
    public Message getMessages(String path, String seq) {
        path = path+seq;
        return getMessages(path).get(0);
    }

    public List<Message> getMessages(String path, String myId, String friendId) {
        if(friendId.equals("")) path = "/ids/"+myId+path;
        else path="/ids/"+myId+"/from/"+friendId;
        return getMessages(path);
    }

    public Message postMessage(String path, Message msg) {
        path = "/ids/"+msg.getFromid()+path;
        JsonNode response = TransactionController.post(path,objectToJSON(msg));
      //  return response.toString();
        return messagesFromJSON("["+response.toString()+"]").get(0);
        //return responseList.get(0);
    }

    public List<Message> messagesFromJSON(String json){
        try {
            List<Message> messages = om.readValue(json,
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
            return messages;
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