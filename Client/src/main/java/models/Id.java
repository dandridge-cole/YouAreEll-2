package models;

/* 
 * POJO for an Id object
 */
public class Id extends Data{
    private String name;
    private String githubID;

    public Id (String name, String githubId) {
        this.name=name;
        this.githubID=githubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithubID() {
        return githubID;
    }

    public void setGithubID(String githubID) {
        this.githubID = githubID;
    }

}