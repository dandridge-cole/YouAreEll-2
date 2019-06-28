package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Data;
import kong.unirest.JsonNode;
import models.Id;
import models.Message;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DataController<E extends Data> {

    private ObjectMapper om = new ObjectMapper();

    List<Message> messagesFromJSON(JsonNode json){
        try {
            List<Message> objects = Arrays.asList(om.readValue(json.toString(), Message[].class));
            return objects;
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
            return null;
        }
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
/*
    List<E> objectFromJSON(JsonNode json){
        try {
          //  JSONArray jsa = json.getArray();
          //  ArrayList<E> objects= new ArrayList<>();
          //  for(Object object:jsa)objects.add(om.readValue(object.toString(), E));
            List<E> objects = Arrays.asList(om.readValue(json.toString(), E[].class));
            return objects;
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
            return null;
        }
    }*/


    String objectToJSON(E object){
        try{
            String json = om.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException jpe){
            System.out.println(jpe.getMessage());
            return null;
        }
    }
}
/*
  "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});

 */