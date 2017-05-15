package com.asian5restaurant.lemongrass.Model;

/**
 * Created by Jigin on 2/22/2017.
 */

public class SubscriptionModel
{
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String name,email;
}
