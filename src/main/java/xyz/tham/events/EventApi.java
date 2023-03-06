package xyz.tham.events;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
