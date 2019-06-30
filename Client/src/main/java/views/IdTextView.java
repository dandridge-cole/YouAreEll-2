package views;

import models.Id;

public class IdTextView {
    private Id id;
    public IdTextView(Id idToDisplay) {
        this.id=idToDisplay;
    }
    @Override public String toString() {
        return ("\n\t{" +
                "\n\t\t\"userid\":" + this.id.getUserid() +
                "\n\t\t\"name\":" + this.id.getName() +
                "\n\t\t\"github\":" + this.id.getGithub() +
                "\n\t}");
    } 
}