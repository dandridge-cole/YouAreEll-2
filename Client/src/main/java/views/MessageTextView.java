package views;

import models.Message;

public class MessageTextView {

    private Message msg;

    public MessageTextView(Message msgToDisplay) {
        this.msg = msgToDisplay;
    }
    @Override public String toString() {
        return ("\n\t{" +
                "\n\t\t\"sequence\":" + this.msg.getSequence() +
                "\n\t\t\"timestamp\":" + this.msg.getTimestamp() +
                "\n\t\t\"fromid\":" + this.msg.getFromid() +
                "\n\t\t\"toid\":" + this.msg.getToid() +
                "\n\t\t\"message\":" + this.msg.getMessage() +
                "\n\t}");
    }
}