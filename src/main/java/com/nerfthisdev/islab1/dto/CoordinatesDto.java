package com.nerfthisdev.islab1.dto;

public class CoordinatesDto {
    public Long id;
    public long x;
    public Double y;

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
