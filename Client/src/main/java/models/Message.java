package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import views.MessageTextView;

import java.util.ArrayList;
import java.util.List;


/*
 * POJO for an Message object
 */
public class Message extends Data{
    private String message;
    private String fromid;
    private String toid;
    private String sequence;
    private String timestamp;

    private ObjectMapper om = new ObjectMapper();
    List<String> list = new ArrayList<>();

    public Message (String message, String fromid, String toid) {
        this.message=message;
        this.fromid=fromid;
        this.toid=toid;
    }

    public Message (){
    }
    /*
    public Message (String message, String fromid, String toid, String sequence, String timestamp) {
        this.message=message;
        this.fromid=fromid;
        this.toid=toid;
        this.sequence=sequence;
        this.timestamp=timestamp;
    }
    @JsonCreator
    public Message (
            @JsonProperty("message") String message,
            @JsonProperty("fromid") String fromid,
            @JsonProperty("toid") String toid) {
        this.message=message;
        this.fromid=fromid;
        this.toid=toid;
    }


    @JsonCreator
    public Message(
            @JsonProperty("message") String message,
            @JsonProperty("fromid") String fromid,
            @JsonProperty("toid") String toid,
            @JsonProperty("sequence") String sequence,
            @JsonProperty("timestamp") String timestamp) {
        this.message=message;
        this.fromid=fromid;
        this.toid=toid;
        this.sequence=sequence;
        this.timestamp=timestamp;
    }*/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String toString(){
        MessageTextView mtv = new MessageTextView(this);
        return mtv.toString();
    }


}
/*
public class BeanWithCreator {
    public int id;
    public String name;

    @JsonCreator
    public BeanWithCreator(
      @JsonProperty("id") int id,
      @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }
}
 */