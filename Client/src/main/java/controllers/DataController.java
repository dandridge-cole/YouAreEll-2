package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Data;
import models.Id;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class DataController<E extends Data> {

    private ObjectMapper om = new ObjectMapper();
    ArrayList<E> objectFromJSON(String json){
        try {
            ArrayList<E> object = om.readValue(json, new TypeReference<List<E>>() {});
            return object;
        } catch (IOException ioe){
            System.out.println(ioe.getStackTrace());
            return null;
        }
    }
    String objectToJSON(E object){
        try{
            String json = om.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException jpe){
            System.out.println(jpe.getStackTrace());
            return null;
        }
    }
}
