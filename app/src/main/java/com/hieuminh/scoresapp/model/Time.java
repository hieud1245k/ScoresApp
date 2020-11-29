package com.hieuminh.scoresapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;

public class Time {

    public int hour;
    public int min;
    public int second;
    public int day;
    public int month;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Time() {
        this.month = LocalDate.now().getMonthValue();
        this.day = LocalDate.now().getDayOfMonth();
        this.hour = LocalTime.now().getHour();
        this.min = LocalTime.now().getMinute();
        this.second = LocalTime.now().getSecond();
    }
}
