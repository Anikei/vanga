package com.anikei.vanga.leadTime;

import java.io.Serializable;
import java.util.Comparator;

public class TimeEntry implements Serializable {

    final String date;
    final int time;

    TimeEntry(String date, String duration) {
        this.date = date;

        //time parsing
        String[] times = duration.split(":");
        time =  Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

    public static final Comparator<TimeEntry> DESC_BY_TIME = new Comparator<>() {
        @Override
        public int compare(TimeEntry lhs, TimeEntry rhs) {
            return (int) ((rhs.time - lhs.time) * 10);//костыль сравнения дробных показателей
        }
    };

}
