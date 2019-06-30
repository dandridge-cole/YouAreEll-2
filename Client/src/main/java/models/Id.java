package models;

import views.IdTextView;

/*
 * POJO for an Id object
 */
public class Id extends Data{
    private String userid;
    private String name;
    private String github;

    public Id (String userid, String name, String github) {
        this.userid=userid;
        this.name=name;
        this.github=github;
    }

    public Id(){
    }

    public String getUserid() {return userid;}

    public void setUserid(String userid) {this.userid = userid;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }


    public String toString(){
        IdTextView itv = new IdTextView(this);
        return itv.toString();
    }
}