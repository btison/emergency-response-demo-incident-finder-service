package com.redhat.emergency.response.incident.finder.model;

import java.math.BigDecimal;
import java.util.List;

public class Mission {

    private String id;
    private String incidentId;
    private String responderId;

    private BigDecimal responderStartLat;

    private BigDecimal responderStartLong;

    private BigDecimal incidentLat;

    private BigDecimal incidentLong;

    private BigDecimal destinationLat;

    private BigDecimal destinationLong;

    private List<ResponderLocationHistory> responderLocationHistory;

    private List<MissionStep> steps;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getResponderId() {
        return responderId;
    }

    public void setResponderId(String responderId) {
        this.responderId = responderId;
    }

    public BigDecimal getResponderStartLat() {
        return responderStartLat;
    }

    public void setResponderStartLat(BigDecimal responderStartLat) {
        this.responderStartLat = responderStartLat;
    }

    public BigDecimal getResponderStartLong() {
        return responderStartLong;
    }

    public void setResponderStartLong(BigDecimal responderStartLong) {
        this.responderStartLong = responderStartLong;
    }

    public BigDecimal getIncidentLat() {
        return incidentLat;
    }

    public void setIncidentLat(BigDecimal incidentLat) {
        this.incidentLat = incidentLat;
    }

    public BigDecimal getIncidentLong() {
        return incidentLong;
    }

    public void setIncidentLong(BigDecimal incidentLong) {
        this.incidentLong = incidentLong;
    }

    public BigDecimal getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(BigDecimal destinationLat) {
        this.destinationLat = destinationLat;
    }

    public BigDecimal getDestinationLong() {
        return destinationLong;
    }

    public void setDestinationLong(BigDecimal destinationLong) {
        this.destinationLong = destinationLong;
    }

    public List<ResponderLocationHistory> getResponderLocationHistory() {
        return responderLocationHistory;
    }

    public void setResponderLocationHistory(List<ResponderLocationHistory> responderLocationHistory) {
        this.responderLocationHistory = responderLocationHistory;
    }

    public List<MissionStep> getSteps() {
        return steps;
    }

    public void setSteps(List<MissionStep> steps) {
        this.steps = steps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
