package com.meowlomo.atm.quartz.model;

public class CronDetails {

    private Integer hours;
    private Integer minutes;
    private Integer seconds;
    private String dayofMonth;
    private String month;
    private String dayOfWeek;
    private String year;

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public String getDayofMonth() {
        return dayofMonth;
    }

    public void setDayofMonth(String dayofMonth) {
        this.dayofMonth = dayofMonth;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
