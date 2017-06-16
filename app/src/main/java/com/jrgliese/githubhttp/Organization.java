package com.jrgliese.githubhttp;

/**
 * Created by Jörg.Liese on 16.06.2017.
 */

public class Organization {

    private String login;
    private String description;

    public String getDescription() {
        return description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
