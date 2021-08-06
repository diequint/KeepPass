package com.diequint.keeppass;

public class ListElement {
    int id;
    public String colour, category, serviceName, userName, password, siteURL;

    public ListElement(int id, String colour, String category, String serviceName, String userName, String password, String siteURL) {
        this.id = id;
        this.colour = colour;
        this.category = category;
        this.serviceName = serviceName;
        this.userName = userName;
        this.password = password;
        this.siteURL = siteURL;
    }

    public ListElement(String colour, String serviceName, String userName, String siteURL) { //Just for development purpouses
        this.colour = colour;
        this.serviceName = serviceName;
        this.userName = userName;
        this.siteURL = siteURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }
}
