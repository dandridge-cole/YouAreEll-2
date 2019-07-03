package controllers;

import com.sun.xml.internal.bind.v2.model.core.ID;
import models.Id;
import models.Message;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class IdControllerTest {
    IdController idctrl = new IdController();
    List<Id> ids;
    String path = "/ids";

    @Test
    public void getIds() {
        ids = idctrl.getIds();
    //    Integer expected = 101;
        Integer actual = ids.size();
        Assert.assertTrue(actual>100);
    }

    @Test
    public void checkIfExistsFalse() {
        String actual = idctrl.checkIfExists("thisiddoesntexist");
        Assert.assertNull(actual);
    }

    @Test
    public void checkIfExistsTrue() {
        String actual = idctrl.checkIfExists("spooky");
        Assert.assertNotNull(actual);
    }

    @Test
    public void idsFromJSON() {
        String json = "[\n" +
                "    {\n" +
                "        \"userid\": \"6924bc9592f1b159df3bb4bf254a6f70b75bf1fa\",\n" +
                "        \"name\": \"Zip Code Ghost\",\n" +
                "        \"github\": \"spooky\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"userid\": \"69953268bc3b1ad0ce96b1acc7590e28b4fc2373\",\n" +
                "        \"name\": \"BenTestPut\",\n" +
                "        \"github\": \"BenTestPut\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"userid\": \"6b3608090ef95c9c7857c4626b6a95a9de3a750c\",\n" +
                "        \"name\": \"Why\",\n" +
                "        \"github\": \"whywhy\"\n" +
                "    }]";
        ids=idctrl.idsFromJSON(json);
        Integer expected = 3;
        Integer actual = ids.size();
    }

    @Test
    public void postId() {
        Random random = new Random();
        String json = "[\n" +
                "    {\n" +
                "        \"userid\": \"-\",\n" +
                "        \"name\": \"Zip Code Ghost\",\n" +
                "        \"github\": \"spooky"+random.nextInt(9999)+"\"\n" +
                "    }]";
        Id expected = idctrl.idsFromJSON(json).get(0);
        Id actual = idctrl.postId(path,expected);
        Assert.assertEquals(expected.getGithub(),actual.getGithub());
        Assert.assertEquals(expected.getName(),actual.getName());
        Assert.assertNotEquals(expected.getUserid(),actual.getUserid());
    }

    @Test
    public void putId() {
        String json = "[\n" +
                "    {\n" +
                "        \"userid\": \"6924bc9592f1b159df3bb4bf254a6f70b75bf1fa\",\n" +
                "        \"name\": \"Zip Code Ghost\",\n" +
                "        \"github\": \"spooky\"\n" +
                "    }]";
        Id expected = idctrl.idsFromJSON(json).get(0);
        Id actual = idctrl.putId(path,expected);
        Assert.assertEquals(expected.getGithub(),actual.getGithub());
        Assert.assertEquals(expected.getName(),actual.getName());
        Assert.assertEquals(expected.getUserid(),actual.getUserid());
    }
}