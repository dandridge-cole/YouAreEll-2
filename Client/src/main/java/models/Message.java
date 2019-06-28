package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO for an Message object
 */
public class Message extends Data{
    private String message;
    private String fromid;
    private String toid;
    private String sequence;
    private String timestamp;

    public Message (String message, String fromid, String toid, String sequence, String timestamp) {
        this.message=message;
        this.fromid=fromid;
        this.toid=toid;
        this.sequence=sequence;
        this.timestamp=timestamp;
    }
    public Message (String message, String fromid, String toid) {
        this.message=message;
        this.fromid=fromid;
        this.toid=toid;
    }
   /* @JsonCreator
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

    public String getFromId() {
        return fromid;
    }

    public void setFromId(String fromid) {
        this.fromid = fromid;
    }

    public String getToId() {
        return toid;
    }

    public void setToId(String toid) {
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