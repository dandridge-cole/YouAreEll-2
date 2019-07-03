package controllers;

import models.Id;
import models.Message;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MessageControllerTest {

    MessageController msgctrl = new MessageController();
    List<Message> msgs;
    String path = "/messages";
    String seqid = "832e67b002b818c8edcf48b272ffb8cde45ae128";
    String sampleJSON = "[\n" +
            "    {\n" +
            "        \"sequence\": \"3bb25c041b071c4bbd3185d001e6f5733e68801d\",\n" +
            "        \"timestamp\": \"2019-07-01T18:52:45.391933638Z\",\n" +
            "        \"fromid\": \"newbie\",\n" +
            "        \"toid\": \"fakename\",\n" +
            "        \"message\": \"hi\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"sequence\": \"832e67b002b818c8edcf48b272ffb8cde45ae128\",\n" +
            "        \"timestamp\": \"2019-07-01T18:48:42.129717578Z\",\n" +
            "        \"fromid\": \"newbie\",\n" +
            "        \"toid\": \"tricksareforkids\",\n" +
            "        \"message\": \"do I work without sequence?\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"sequence\": \"4884a446e80a34b0930b2d95cdab27a38c158fca\",\n" +
            "        \"timestamp\": \"2019-07-01T18:48:29.701918991Z\",\n" +
            "        \"fromid\": \"newbie\",\n" +
            "        \"toid\": \"tricksareforkids\",\n" +
            "        \"message\": \"hi\"\n" +
            "    }]";

    @Test
    public void getMessages() {
        msgs = msgctrl.getMessages(path);
        Integer expected = 21;
        Integer actual = msgs.size();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getMessages1() {
        Message actual = msgctrl.getMessages(path,seqid);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getMessage());
        Assert.assertNotNull(actual.getFromid());
        Assert.assertNotNull(actual.getToid());
    }

    @Test
    public void getMessages2() {
        Message msg = msgctrl.getMessages(path,seqid);
        String toId = msg.getToid();
        msgs = msgctrl.getMessages(path,new Id(toId,null,null));
        Assert.assertTrue(msgs.size()>0);
    }

    @Test
    public void getMessages3() {
        Message msg = msgctrl.getMessages(path,seqid);
        String toId = msg.getToid();
        String fromId = msg.getFromid();
        msgs = msgctrl.getMessages(path,toId,fromId);
        Assert.assertTrue(msgs.size()>0);
    }

    @Test
    public void postMessage(){
        String msgOut="[{\n" +
                "        \"sequence\": \"-\",\n" +
                "        \"fromid\": \"spooky\",\n" +
                "        \"toid\": \"newbie\",\n" +
                "        \"message\": \"Thankyou, JUnit\"\n" +
                "    }]";
        Message expected = msgctrl.messagesFromJSON(msgOut).get(0);
        Message actual = msgctrl.postMessage(path,expected);
        Assert.assertEquals(expected.getToid(),actual.getToid());
        Assert.assertEquals(expected.getFromid(),actual.getFromid());
        Assert.assertEquals(expected.getMessage(),actual.getMessage());
    }

    @Test
    public void messagesFromJSON(){
        msgs=msgctrl.messagesFromJSON(sampleJSON);
        Integer expected = 3;
        Integer actual = msgs.size();
        Assert.assertEquals(expected,actual);
    }
}