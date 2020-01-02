package com.redhat.emergency.response.incident.finder.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Shelter {

    private String name;

    private BigDecimal lat;

    private BigDecimal lon;

    public Shelter(String name, BigDecimal lat, BigDecimal lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public BigDecimal getLon() {
        return lon;
    }


    public static List<Shelter> shelters() {
        List<Shelter> shelterList = new LinkedList<>();
        shelterList.add(new Shelter("Port City Marina", new BigDecimal("34.2461"), new BigDecimal("-77.9519")));
        shelterList.add(new Shelter("Wilmington Marine Center", new BigDecimal("34.1706"), new BigDecimal("-77.949")));
        shelterList.add(new Shelter("Carolina Beach Yacht Club", new BigDecimal("34.0583"), new BigDecimal("-77.8885")));
        return shelterList;
    }
}

