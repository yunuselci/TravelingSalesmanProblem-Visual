package com.company;

public class Points {
    public String cityName;
    public int cityNumber;
    public int x;
    public int y;

    public Points(int cityNumber, int x, int y){
        this.cityNumber = cityNumber;
        this.x = x;
        this.y = y;
    }

    public Points(String cityName, int cityNumber, int x, int y) {
        this.cityName = cityName;
        this.cityNumber = cityNumber;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Points{" +
                "cityName='" + cityName + '\'' +
                ", cityNumber=" + cityNumber +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
