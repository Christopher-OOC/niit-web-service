package com.javalord.niit_web_service.niit_web_service.model.errror;

import java.util.Date;

public class Error {
    private String messsage;
    private Date date;

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
