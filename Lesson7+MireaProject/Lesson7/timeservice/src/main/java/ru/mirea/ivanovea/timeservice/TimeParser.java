package ru.mirea.ivanovea.timeservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeParser {

    public static String parseTime(String timeString) {
        String[] parts = timeString.split(" ");
        String time = parts[2];
        String date = parts[1];

        SimpleDateFormat inputFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        try {
            Date dateTime = inputFormat.parse(date + " " + time);
            String formattedTime = outputFormat.format(dateTime);
            return "Дата: " + formattedTime.substring(0, 10) + ", Время: " + formattedTime.substring(11);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}


