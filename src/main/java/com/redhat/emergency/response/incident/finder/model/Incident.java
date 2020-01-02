package com.redhat.emergency.response.incident.finder.model;

import java.math.BigDecimal;

public class Incident {

    private String id;

    private BigDecimal lat;

    private BigDecimal lon;

    private int numberOfPeople;

    private boolean medicalNeeded;

    private long timestamp;

    private String victimName;

    private String victimPhoneNumber;

    private String status;

    private BigDecimal currentPositionLat;

    private BigDecimal currentPositionLon;

    private BigDecimal destinationLat;

    private BigDecimal destinationLon;

    private String destinationName;

    public String getId() {
        return id;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public boolean isMedicalNeeded() {
        return medicalNeeded;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getVictimName() {
        return victimName;
    }

    public String getVictimPhoneNumber() {
        return victimPhoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setMedicalNeeded(boolean medicalNeeded) {
        this.medicalNeeded = medicalNeeded;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setVictimName(String victimName) {
        this.victimName = victimName;
    }

    public void setVictimPhoneNumber(String victimPhoneNumber) {
        this.victimPhoneNumber = victimPhoneNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getCurrentPositionLat() {
        return currentPositionLat;
    }

    public void setCurrentPositionLat(BigDecimal currentPositionLat) {
        this.currentPositionLat = currentPositionLat;
    }

    public BigDecimal getCurrentPositionLon() {
        return currentPositionLon;
    }

    public void setCurrentPositionLon(BigDecimal currentPositionLon) {
        this.currentPositionLon = currentPositionLon;
    }

    public BigDecimal getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(BigDecimal destinationLat) {
        this.destinationLat = destinationLat;
    }

    public BigDecimal getDestinationLon() {
        return destinationLon;
    }

    public void setDestinationLon(BigDecimal destinationLon) {
        this.destinationLon = destinationLon;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
}
