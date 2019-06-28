package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

//    public Message (String message, String fromid, String toid) {
//        this.message=message;
//        this.fromid=fromid;
//        this.toid=toid;
//    }
//
//    public Message (){
//
//    }
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
        StringBuilder builder = new StringBuilder();
        builder.append("\n\t{" +
                "\n\t\t\"sequence\":"+this.sequence+
                "\n\t\t\"timestamp\":"+this.timestamp+
                "\n\t\t\"fromid\":"+this.fromid+
                "\n\t\t\"toid\":"+this.toid+
                "\n\t\t\"message\":"+this.message+
                "\n\t}");
//        try{
//            String json = om.writeValueAsString(this);
//            return json;
//        } catch (JsonProcessingException jpe){
//            System.out.println(jpe.getMessage());
//            return null;
//        }
        return builder.toString();
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