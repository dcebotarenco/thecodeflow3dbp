package com.codeflow.application;

public class ContainerDTO {
    private Long id;
    private Double width;
    private Double length;
    private Double height;

    public ContainerDTO(Long id, Double width, Double length, Double height) {
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
