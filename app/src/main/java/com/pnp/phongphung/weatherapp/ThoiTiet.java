package com.pnp.phongphung.weatherapp;

/**
 * Created by PhongPhung on 4/4/2018.
 */

public class ThoiTiet {
    private String day;
    private String status;
    private String image;
    private String maxTemp;
    private String minTemp;

    public ThoiTiet() {
    }

    public ThoiTiet(String day) {
        this.day = day;
    }

    public ThoiTiet(String day, String status, String image, String maxTemp, String minTemp) {
        this.day = day;
        this.status = status;
        this.image = image;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }
}
