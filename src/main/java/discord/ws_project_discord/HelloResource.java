package discord.ws_project_discord;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        ApplicationListener.getEmf().createEntityManager();
        return "Hello, World!" ;
    }
}