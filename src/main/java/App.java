import com.jacek.rest.TransferController;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;

public class App {
    private static final String BASE_URI = "http://localhost:8080/";

    public static void main(String[] args) {
        ResourceConfig rc = new ResourceConfig().registerClasses(TransferController.class);
        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }
}


