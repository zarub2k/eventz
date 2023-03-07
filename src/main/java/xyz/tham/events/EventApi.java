package xyz.tham.events;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventApi {
    @Inject EventManager eventManager;

    @GET
    public Response all() {
        return Response
                .ok()
                .entity(eventManager.all())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Event event) {
        eventManager.create(event);
        return Response
                .created(null)
                .build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") int id) {
        return Response
                .ok()
                .entity(eventManager.get(id))
                .build();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        eventManager.delete(id);
    }
}
