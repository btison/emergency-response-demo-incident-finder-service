package com.redhat.emergency.response.incident.finder.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.emergency.response.incident.finder.model.Incident;
import com.redhat.emergency.response.incident.finder.streams.InteractiveQueries;

@ApplicationScoped
@Path("/")
public class IncidentEndpoint {

    @Inject
    InteractiveQueries interactiveQueries;

    @GET
    @Path("/incident/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIncident(@PathParam("id") String id) {
        Incident result = interactiveQueries.getIncident(id);
        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        } else {
            return Response.ok(result).build();
        }
    }

}
