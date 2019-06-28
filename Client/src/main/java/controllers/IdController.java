package controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.JsonNode;
import models.Id;


public class IdController extends DataController<Id>{
    Id myId;
    private ObjectMapper om = new ObjectMapper();

    public ArrayList<Id> getIds() {
        return null;
    }

    public Id postId(Id id) {
        return null;
    }

    public Id putId(Id id) {
        return null;
    }


    List<Id> idsFromJSON(JsonNode json){
        try {
            List<Id> objects = Arrays.asList(om.readValue(json.toString(), Id[].class));
            return objects;
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
            return null;
        }
    }

    private ArrayList<Id> idFromJSON(String json){
        try {
            ArrayList<Id> id = om.readValue(json, new TypeReference<List<Id>>() {});
            return id;
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