package com.redhat.emergency.response.incident.finder.model;

import java.math.BigDecimal;

public class MissionStep {

    private BigDecimal lat;

    private BigDecimal lon;

    private boolean isWayPoint = false;

    private boolean isDestination = false;

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

    public boolean isWayPoint() {
        return isWayPoint;
    }

    public void setWayPoint(boolean wayPoint) {
        isWayPoint = wayPoint;
    }

    public boolean isDestination() {
        return isDestination;
    }

    public void setDestination(boolean destination) {
        isDestination = destination;
    }
}
