package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Data;


public abstract class DataController<E extends Data> {

    private ObjectMapper om = new ObjectMapper();


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