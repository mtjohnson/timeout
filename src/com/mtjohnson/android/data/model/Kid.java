package com.mtjohnson.android.data.model;

import java.util.Calendar;
import java.util.Date;

public class Kid {
    private int id;
    private String name;
    private Date birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getDefaultMinutes() {
        Calendar then = Calendar.getInstance();
        then.clear();
        then.setTime(birthDate);
        Calendar now = Calendar.getInstance();
        Calendar clone = (Calendar) then.clone();
        int years = -1;
        while (!clone.after(now)) {
            clone.add(Calendar.YEAR, 1);
            years++;
        }
        return years;
    }
}
