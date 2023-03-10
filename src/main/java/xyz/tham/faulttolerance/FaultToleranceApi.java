package xyz.tham.faulttolerance;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import xyz.tham.events.Event;
import xyz.tham.events.EventManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Path("v1/faulttolerance")
public class FaultToleranceApi {
    @Inject
    EventManager manager;

    private AtomicLong counter = new AtomicLong(0);

    @GET
    @Path("recommendations")
    @Retry(maxRetries = 5)
    public List<Event> recommendations() {
        final Long invocationNumber = counter.getAndIncrement();
        String message = String.format("Recommendations invocation #%d failed",
                invocationNumber);
        mayFail(String.format(message));
        System.out.println(message);
        return manager.recommendations();
    }

    @GET
    @Path("timeout")
    @Timeout(250)
    @Fallback(fallbackMethod = "fallback")
//    @Retry(maxRetries = 3)
    public String timeout() {
        long start = System.currentTimeMillis();
        long invocationNumber = counter.getAndIncrement();
        System.out.println("Enters " + invocationNumber);
        try {
            purposefulDelay();
            return "Successful result";
        } catch (InterruptedException e) {
            String message = String.format("Timeout invocation #%d timed out after %d ms",
                    invocationNumber, System.currentTimeMillis() - start);
            System.out.println("$$$$" + message);
            return "Inside catch block";
        }
    }

    public String fallback() {
        return "Fallback method called";
    }

    private void purposefulDelay() throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
    }

    private void mayFail(String message) {
        if (new Random().nextBoolean()) {
//            System.err.println("Error inside mayFail()");
            throw new RuntimeException("Failed here");
        }
    }
}
