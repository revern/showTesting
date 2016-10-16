package com.example.almaz.showtesting.models;

/**
 * Created by almaz on 16.10.2016.
 */

public class User {
    String mFirstName;
    String mLastName;

    public  User(String firstname, String lastname){
        mFirstName=firstname;
        mLastName=lastname;
    }
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }
}
