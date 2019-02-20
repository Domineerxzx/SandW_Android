package com.triplebro.aran.sandw.views;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerListener implements DatePickerDialog.OnDateSetListener {

    private TextView tv_date;
    private int year_now;
    private int month_now;
    private int day_now;


    public DatePickerListener(TextView tv_date, int year_now, int month_now, int day_now) {
        this.tv_date = tv_date;
        this.year_now = year_now;
        this.month_now = month_now;
        this.day_now = day_now;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        year_now = year;
        month_now = month;
        day_now = dayOfMonth;
        String days;
        if (month_now + 1 < 10) {
            if (day_now < 10) {
                days = new StringBuffer().append(year_now).append("年").append("0").
                        append(month_now + 1).append("月").append("0").append(day_now).append("日").toString();
            } else {
                days = new StringBuffer().append(year_now).append("年").append("0").
                        append(month_now + 1).append("月").append(day_now).append("日").toString();
            }
        } else {
            if (day_now < 10) {
                days = new StringBuffer().append(year_now).append("年").
                        append(month_now + 1).append("月").append("0").append(day_now).append("日").toString();
            } else {
                days = new StringBuffer().append(year_now).append("年").
                        append(month_now + 1).append("月").append(day_now).append("日").toString();
            }
        }
        tv_date.setText(days);
    }
}
