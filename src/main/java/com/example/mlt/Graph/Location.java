package com.example.mlt.Graph;

public class Location implements Displayable {
    private int x, y;
    private String name;
    //Create a new location constructor
    public Location(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Location() {
    }
    //Getter of x , y and name
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }

    //Setter of x and y
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }




}
