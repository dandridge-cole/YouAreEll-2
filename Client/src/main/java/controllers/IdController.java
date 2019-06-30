package controllers;


import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import models.Id;


public class IdController extends DataController<Id>{
    Id myId;
    private ObjectMapper om = new ObjectMapper();

    public List<Id> getIds() {
        String path = "/ids";
        HttpResponse<JsonNode> response = TransactionController.get(path);
        return idsFromJSON(response.getBody().toString());
    }

    public Id postId(String path, Id id) {
        JsonNode response = TransactionController.post(path,objectToJSON(id));
     //   return response.toString();
         return idsFromJSON("["+response.toString()+"]").get(0);
    }

    public Id putId(String path, Id id) {
        JsonNode response = TransactionController.put(path,objectToJSON(id));
   //     return response.toString();
        return idsFromJSON("["+response.toString()+"]").get(0);
    }

    public String checkIfExists(String github){
        List<Id> list = getIds();
        for (Id id:list) if(id.getGithub().equals(github)) return id.getUserid();
        return null;
    }

//    List<Id> idsFromJSON(JsonNode json){
//        try {
//            List<Id> objects = Arrays.asList(om.readValue(json.toString(), Id[].class));
//            return objects;
//        } catch (IOException ioe){
//            System.out.println(ioe.getMessage());
//            return null;
//        }
//    }

    public List<Id> idsFromJSON(String json){
        try {
            List<Id> ids = om.readValue(
                    json,new TypeReference<List<Id>>() { });
          //  ArrayList<Id> ids = om.readValue(json, new TypeReference<List<Id>>() {});
            return ids;
        } catch (IOException ioe){
            System.out.println(ioe.getStackTrace());
            return null;
        }
    }
/*
    private String idToJSON(Id id){
        try{
            String json = om.writeValueAsString(id);
            return json;
        } catch (JsonProcessingException jpe){
            System.out.println(jpe.getStackTrace());
            return null;
        }
    }*/
 
}