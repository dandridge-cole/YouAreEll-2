package controllers;


import java.util.ArrayList;
import models.Id;


public class IdController extends DataController<Id>{
    Id myId;

    public ArrayList<Id> getIds() {
        return null;
    }

    public Id postId(Id id) {
        return null;
    }

    public Id putId(Id id) {
        return null;
    }


/*
    private ObjectMapper om = new ObjectMapper();
    private ArrayList<Id> iDFromJSON(String json){
        try {
            ArrayList<Id> id = om.readValue(json, new TypeReference<List<Id>>() {});
            return id;
        } catch (IOException ioe){
            System.out.println(ioe.getStackTrace());
            return null;
        }
    }
    private String iDoJSON(Id id){
        try{
            String json = om.writeValueAsString(id);
            return json;
        } catch (JsonProcessingException jpe){
            System.out.println(jpe.getStackTrace());
            return null;
        }
    }*/
 
}