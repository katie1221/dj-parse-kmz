package com.example.djpointdemo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Gps {
    private Double lon;
    private Double lat;

    public Gps() {
    }

    public Gps(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "{" + lat + "," + lon + "}";
    }
}
