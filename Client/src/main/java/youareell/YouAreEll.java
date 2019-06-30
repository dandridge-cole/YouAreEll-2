package youareell;

import controllers.*;
import models.Id;
import models.Message;

import java.util.List;

public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;

    public YouAreEll (MessageController m, IdController j) {
        // used j because i seems awkward
        this.msgCtrl = m;
        this.idCtrl = j;
    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(new MessageController(), new IdController());
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public String get_ids(String method, String payload) {
        return MakeURLCall("/ids", method, payload);
    }

    public String get_messages(String method, String payload) {
        return MakeURLCall("/messages", method, payload);
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        switch (mainurl){
            case "/ids": {
                switch(method) {
                    case "GET":
                        return idCtrl.getIds().toString();
                    case "POST": {
                        Id id = idCtrl.idsFromJSON("["+jpayload+"]").get(0);
                        String userId=null;
                        if(id!=null&&id.getGithub()!=null)userId=idCtrl.checkIfExists(id.getGithub());
                        if(userId==null) return idCtrl.postId(mainurl,id).toString();
                        else{
                            id.setUserid(userId);
                            return idCtrl.putId(mainurl,id).toString();
                        }
                    }
                }
            }
            case "/messages":{
                switch(method) {
                    case "GET": {
                        if (jpayload.equals("")) return msgCtrl.getMessages(mainurl).toString();
                        else{
                            List<Id> list = idCtrl.idsFromJSON("["+jpayload+"]");
                            if(list==null||list.size()==0)return msgCtrl.getMessages(mainurl).toString();
                            if (list.size() == 1)return msgCtrl.getMessages(mainurl, list.get(0).getGithub(),"").toString();
                            return msgCtrl.getMessages(mainurl,list.get(0).getGithub(),list.get(1).getGithub()).toString();
                        }
                    }
                    case "POST":{
                        Message msg = msgCtrl.messagesFromJSON("["+jpayload+"]").get(0);
                        return msgCtrl.postMessage(mainurl,msg).toString();
                    }
                }
            }
        }
        return "nada";
    }
}
