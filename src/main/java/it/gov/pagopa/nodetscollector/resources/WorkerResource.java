package it.gov.pagopa.nodetscollector.resources;

import io.netty.handler.codec.http.HttpResponseStatus;
import it.gov.pagopa.nodetscollector.entities.Worker;
import it.gov.pagopa.nodetscollector.models.ProblemJson;
import it.gov.pagopa.nodetscollector.models.requests.WorkerRequest;
import it.gov.pagopa.nodetscollector.services.WorkerService;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Path("/workers")
@Produces(value = MediaType.APPLICATION_JSON)
public class WorkerResource implements Serializable {

    @Inject
    WorkerService workerService;

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Worker.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProblemJson.class))),
            @APIResponse(responseCode = "500", description = "Service unavailable.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProblemJson.class)))
    })
    @GET
    public Response workerList() {
        return Response.ok(workerService.getWorkers()).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Worker.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProblemJson.class))),
            @APIResponse(responseCode = "500", description = "Service unavailable.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProblemJson.class)))
    })
    @Consumes(value = MediaType.APPLICATION_JSON)
    @POST
    public Response createWorker(@NotNull @Valid WorkerRequest workerRequest) {
        return Response.status(HttpResponseStatus.CREATED.code()).entity(workerService.createWorker(workerRequest)).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Worker.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProblemJson.class))),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProblemJson.class))),
            @APIResponse(responseCode = "500", description = "Service unavailable.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProblemJson.class)))
    })
    @Consumes(value = MediaType.APPLICATION_JSON)
    @GET
    @Path("/{id}")
    public Response getWorker(@PathParam("id") Long id) {
        return Response.ok(workerService.getWorker(id)).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Worker.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProblemJson.class))),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProblemJson.class))),
            @APIResponse(responseCode = "500", description = "Service unavailable.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProblemJson.class)))
    })
    @Consumes(value = MediaType.APPLICATION_JSON)
    @DELETE
    @Path("/{id}")
    public Response deleteWorker(@PathParam("id") Long id) {
        workerService.deleteWorker(id);
        return Response.ok().build();
    }
}
