package com.codeflow.domain.container;

public class Container {

    private Long id;
    private Double width;
    private Double length;
    private Double height;

    Container(Long id, Double width, Double length, Double height) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public Long getId() {
        return id;
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
