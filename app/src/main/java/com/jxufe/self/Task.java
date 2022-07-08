package com.jxufe.self;

import java.util.Calendar;

public class Task {
    private String name;//Task name
    private Calendar Deadline;//Task Time
    private String TomatoTime;//Maybe Using Calendar
    private Calendar FinishTime;
    public Boolean status = false;

    public Task(String name, Calendar Deadline, String TomatoTime) {
        this.name = name;
        this.Deadline = Deadline;
        this.TomatoTime = TomatoTime;
    }
    public Task(){

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDeadline(Calendar deadline) {
        Deadline = deadline;
    }

    public void setTomatoTime(String tomatoTime) {
        TomatoTime = tomatoTime;
    }

    public void setFinishTime(Calendar finishTime) {FinishTime = finishTime;}

    public String getName() {
        return name;
    }

    public Calendar getDeadline() {
        return Deadline;
    }

    public Calendar getFinishTime() {return FinishTime;}

    public String getTomatoTime() {
        return TomatoTime;
    }
}

