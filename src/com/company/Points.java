package com.company;

public class Points {
    public int cityNumber;
    public int x;
    public int y;

    public Points(int cityNumber, int x, int y){
        this.cityNumber = cityNumber;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Points{" +
                "cityNumber=" + cityNumber +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
