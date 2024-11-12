package iuh.week02_lab_huynhhoangphuc_21036541.backend.Resource;


import iuh.week02_lab_huynhhoangphuc_21036541.backend.services.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/orders")
public class OrderResource {
    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    public OrderResource() {
        orderService = new OrderService();
    }
    @GET
    @Produces("application/json")
    public Response getAll() {
        Response.ResponseBuilder response = Response.ok();
        response.entity(orderService.getAll());
        return response.build();
    }

}
