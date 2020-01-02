package com.redhat.emergency.response.incident.finder.model;

import java.math.BigDecimal;

public class ResponderLocationHistory{

    private long timestamp;

    private BigDecimal lat;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    private BigDecimal lon;
}
