package com.codeflow.application.client;

public class Orientation {
    private Double width;
    private Double length;
    private Double height;

    public Orientation(Double width, Double height, Double length) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public Double getLength() {
        return length;
    }

    public Double getHeight() {
        return height;
    }
}
