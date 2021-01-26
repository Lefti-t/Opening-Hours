package com.company;

import java.sql.Timestamp;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        Timestamp ts = java.sql.Timestamp.valueOf("2021-01-25 17:29:00"); //tested day is Monday
        System.out.println(isOpen(ts));
        nextStatusChange(ts);
    }

    public static boolean isOpen(Timestamp timestamp) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);

        int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int hours = cal.get(java.util.Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);


        //Mondays, Tuesdays and Thursdays from 8:00–12:00 and 13:00–17:30
        if ((day == 2 || day == 3 || day == 5) &&
                ((hours >= 8 && hours < 12) || (hours >= 13 && hours < 17) || (hours == 17 && minutes <= 29))) {
            return true;
        }
        //Wednesdays from 8:00–13:00
        if (day == 4 && hours >= 8 && hours < 13) {
            return true;
        }
        //Fridays from 8:00–12:00 and 13:00–20:00
        if (day == 6 &&
                ((hours >= 8 && hours < 12) || (hours >= 13 && hours < 20))) {
            return true;
        }
        //Saturdays from 10:00–13:00
        if (day == 7 && hours >= 10 && hours < 13) {
            return true;
        } else return false;
    }


    public static void nextStatusChange(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);

        int days = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int hours = cal.get(java.util.Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);


        boolean status = isOpen(timestamp);
        for (int day = days; day < 7; day++) {

            for (int hour = hours; hour < 24; hour++) {

                for (int minute = minutes; minute < 60; minute++) {
                    Timestamp ts2 = new Timestamp(cal.getTime().getTime());

                    if (isOpen(ts2) != status) {
                        System.out.println(ts2);
                        return;

                    }
                    minutes = 0;
                    cal.add(Calendar.MINUTE, 1);

                }
                hours = 0;
            }
            days = 0;
        }
    }
}


